package com.bkap.controller.admin;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.IAnswerDAO;
import dao.IClassDAO;
import dao.ILevelDAO;
import dao.IQuestionDAO;
import dao.ISubjectDAO;
import dao.ImplClassDAO;
import dao.ImplSubjectDAO;
import dao.IplmAnswerDAO;
import dao.IplmLevelDAO;
import dao.IplmQuestionDAO;
import entity.Answer;
import entity.Class;
import entity.Question;
import entity.QuestionLevel;
import entity.Subject;

@Controller
@RequestMapping(value = { "/admin" })
public class QuestionController {
	@RequestMapping(value = { "/questions" }, method = RequestMethod.GET)
	public String index(Model model) {

		IQuestionDAO IQuestionDao = new IplmQuestionDAO();
		List<Question> questions = IQuestionDao.select();

		model.addAttribute("questions", questions);

		return "admin/question/list";
	}

	@RequestMapping(value = { "/question-create" }, method = RequestMethod.GET)
	public String create(Model model) {
		ILevelDAO ILevelDao = new IplmLevelDAO();
		List<QuestionLevel> levels = ILevelDao.select();

		ISubjectDAO ISubjectDao = new ImplSubjectDAO();
		List<Subject> subjects = ISubjectDao.select();

		model.addAttribute("levels", levels);
		model.addAttribute("subjects", subjects);

		Question question = new Question();

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("question")) {
			question.setStatus(true);
			model.addAttribute("question", question);
		}

		return "admin/question/create";
	}

	@RequestMapping(value = { "/question-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("level") Question question, BindingResult br,
			HttpServletRequest request, RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.question", br);

			redirect.addFlashAttribute("question", question);
		} else {
			IQuestionDAO IQuestionDao = new IplmQuestionDAO();
			boolean isCheckQuestion = IQuestionDao.insert(question);

			String[] answers = request.getParameterValues("answer_content");
			String status = request.getParameter("answer_status");

			IAnswerDAO IAnswerDao = new IplmAnswerDAO();

			for (int i = 0; i < answers.length; i++) {
				// Insert to answer
				Answer answer = new Answer();

				answer.setContent(answers[i]);
				answer.setStatus(false);
				answer.setAnswersQuestion(question);

				// Check correct answer
				if (i == Integer.parseInt(status)) {
					answer.setStatus(true);
				}

				boolean isCheckAnswer = IAnswerDao.insert(answer);

				if (isCheckAnswer) {
					System.out.println(answer.toString());

					System.out.println("answer " + answers[i]);
				}
			}

			System.out.println("question Id: " + question.getId());

			if (isCheckQuestion) {
				redirect.addFlashAttribute("message", "Create new question successfully!");
			} else {
				redirect.addFlashAttribute("message", "Create new question failed!");

			}
		}

		// Redirect to questions page

		return "redirect:/admin/questions";
	}

	@RequestMapping(value = { "/question-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {

		// Get questions level and sujects
		ILevelDAO ILevelDao = new IplmLevelDAO();
		List<QuestionLevel> levels = ILevelDao.select();

		ISubjectDAO ISubjectDao = new ImplSubjectDAO();
		List<Subject> subjects = ISubjectDao.select();

		model.addAttribute("levels", levels);
		model.addAttribute("subjects", subjects);

		// Get current question
		IQuestionDAO IQuestionDao = new IplmQuestionDAO();
		Question question = IQuestionDao.detail(id);

		System.out.println(question.getListAnswers().toString());

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("question")) {
			model.addAttribute("question", question);
		}

		return "admin/question/edit";
	}

	@RequestMapping(value = { "/question-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("question") Question question, BindingResult br,
			HttpServletRequest request, RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.question", br);

			redirect.addFlashAttribute("question", question);
		} else {
			IAnswerDAO IAnswerDao = new IplmAnswerDAO();

			// Remove old answers
			boolean removeOldAnswers = IAnswerDao.deleteByQuestionId(question.getId());

			if (removeOldAnswers) {
				System.out.println("Remove old success!");
			} else {
				System.out.println("Remove old failed!");
			}

			// Update answers
			String[] answers = request.getParameterValues("answer_content");
			String status = request.getParameter("answer_status");

			for (int i = 0; i < answers.length; i++) {
				// Insert to answer
				Answer answer = new Answer();

				answer.setContent(answers[i]);
				answer.setStatus(false);
				answer.setAnswersQuestion(question);

				// Check correct answer
				if (i == Integer.parseInt(status)) {
					answer.setStatus(true);
				}

				boolean isCheckAnswer = IAnswerDao.insert(answer);

				if (isCheckAnswer) {
					System.out.println(answer.toString());

					System.out.println("answer " + answers[i]);
				}
			}

			IQuestionDAO IQuestionDao = new IplmQuestionDAO();
			boolean isCheck = IQuestionDao.update(question);

			System.out.println("question Id: " + question.getId());

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update question successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update question failed!");

			}
		}

		return "redirect:/admin/questions";
	}

	@RequestMapping(value = { "/question-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {

		IQuestionDAO IQuestionDao = new IplmQuestionDAO();
		boolean isCheck = IQuestionDao.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record failed because it belongto other table!");
		}

		return "redirect:/admin/questions";
	}

}
