package com.bkap.controller.admin;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import antlr.Utils;
import dao.IAnswerDAO;
import dao.IClassDAO;
import dao.ILevelDAO;
import dao.IQuestionDAO;
import dao.ISubjectDAO;
import dao.ITestDAO;
import dao.ImplClassDAO;
import dao.ImplSubjectDAO;
import dao.IplmAnswerDAO;
import dao.IplmLevelDAO;
import dao.IplmQuestionDAO;
import dao.IplmTestDAO;
import entity.Answer;
import entity.Class;
import entity.Question;
import entity.QuestionLevel;
import entity.Subject;
import entity.Test;

@Controller
@RequestMapping(value = { "/admin" })
public class TestController {
	@RequestMapping(value = { "/tests" }, method = RequestMethod.GET)
	public String index(Model model) {

		ITestDAO ITestDao = new IplmTestDAO();
		List<Test> tests = ITestDao.select();

		model.addAttribute("tests", tests);

		return "admin/test/list";
	}

	@RequestMapping(value = { "/test-create" }, method = RequestMethod.GET)
	public String create(Model model) {
		IClassDAO IClassDao = new ImplClassDAO();
		List<Class> listClasses = IClassDao.select();

		model.addAttribute("listClasses", listClasses);

		ISubjectDAO ISubjectDao = new ImplSubjectDAO();
		List<Subject> subjects = ISubjectDao.select();

		model.addAttribute("subjects", subjects);

		Test test = new Test();

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("test")) {
			test.setStatus(true);
			model.addAttribute("test", test);
		}

		return "admin/test/create";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/test-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("test") Test test, Model model, BindingResult br,
			HttpServletRequest request, RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.test", br);

			redirect.addFlashAttribute("test", test);

			return "redirect:/admin/test-create";
		} else {
			System.out.println(test.toString());

			String low = request.getParameter("low");
			String medium = request.getParameter("medium");
			String advanced = request.getParameter("advanced");

			System.out.println("Low: " + low + " -- Medium: " + medium + " ---- High: " + advanced);

			IQuestionDAO IQuestionDao = new IplmQuestionDAO();

			List<Question> listLowQuestions = IQuestionDao.selectRandom(low, 3);
			List<Question> listMediumQuestions = IQuestionDao.selectRandom(medium, 2);
			List<Question> listAdvancedQuestions = IQuestionDao.selectRandom(advanced, 1);

			// Merge 3 List
			List<Question> listQuestions = Stream.of(listLowQuestions, listMediumQuestions, listAdvancedQuestions)
					.flatMap(x -> x.stream()).collect(Collectors.toList());

			// Shuffle
			Collections.shuffle(listQuestions);

			// Set List Question of Test
			Set<Question> targetSet = new HashSet<Question>(listQuestions);

			// Insert to TestQuestion
			test.setListQuestions(targetSet);

//			for (int i = 0; i < listQuestions.size(); i++) {
//				System.out.println(i + " === " + listQuestions.get(i).toString());
//			}

			System.out.println("Size List Question is: " + listQuestions.size());

			// Insert to DB
			ITestDAO ITestDao = new IplmTestDAO();
			boolean isCheck = ITestDao.insert(test);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Create test successfully!");

				return "redirect:/admin/test/" + test.getId() + "/preview";
			} else {
				redirect.addFlashAttribute("message", "Create test failed!");

				return "redirect:/admin/test-create";
			}

		}
	}

	@RequestMapping(value = { "/test/{id}/preview" }, method = RequestMethod.GET)
	public String preview(@PathVariable("id") int id, Model model) {

		ITestDAO ITestDao = new IplmTestDAO();
		Test test = ITestDao.detail(id);

		model.addAttribute("test", test);

		return "admin/test/preview";
	}

	@RequestMapping(value = { "/test/{id}/practice" }, method = RequestMethod.GET)
	public String practice(@PathVariable("id") int id, Model model) {

		ITestDAO ITestDao = new IplmTestDAO();
		Test test = ITestDao.detail(id);

		model.addAttribute("test", test);

		return "admin/test/practice";
	}

	@RequestMapping(value = { "/test-complete" }, method = RequestMethod.POST)
	public String complete(Model model, HttpServletRequest request) {

		String testID = request.getParameter("test_id");

		System.out.println(testID);

		ITestDAO ITestDao = new IplmTestDAO();
		Test test = ITestDao.detail(Integer.parseInt(testID));

		Set<Question> listQuestion = test.getListQuestions();

		int indexQuestion = 1;
		int correct = 0;
		int wrong = 0;
		int totalQuestions = listQuestion.size();

		for (Question q : listQuestion) {
			System.out.println("indexQuestion " + indexQuestion + "===" + q.toString());

			String answer = request.getParameter("answer_" + indexQuestion);

			System.out.println(answer);

			if (Boolean.parseBoolean(answer)) {
				correct++;
			} else {
				wrong++;
			}

			indexQuestion++;

		}

		System.out.println("totalQuestions ===> " + totalQuestions);

		String testStatus = "";

		if ((correct / totalQuestions) >= (correct / totalQuestions)) {
			testStatus = "Pass";
		} else {
			testStatus = "Faild";
		}

		model.addAttribute("test", test);
		model.addAttribute("correct", correct);
		model.addAttribute("wrong", wrong);
		model.addAttribute("totalQuestions", totalQuestions);
		model.addAttribute("testStatus", testStatus);

		return "admin/test/result";
	}

	@RequestMapping(value = { "/test-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {

		// Get tests level and sujects
		ILevelDAO ILevelDao = new IplmLevelDAO();
		List<QuestionLevel> levels = ILevelDao.select();

		ISubjectDAO ISubjectDao = new ImplSubjectDAO();
		List<Subject> subjects = ISubjectDao.select();

		model.addAttribute("levels", levels);
		model.addAttribute("subjects", subjects);

		// Get current test
		IQuestionDAO IQuestionDao = new IplmQuestionDAO();
		Question test = IQuestionDao.detail(id);

		System.out.println(test.getListAnswers().toString());

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("test")) {
			model.addAttribute("test", test);
		}

		return "admin/test/edit";
	}

	@RequestMapping(value = { "/test-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("test") Question test, BindingResult br, HttpServletRequest request,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.test", br);

			redirect.addFlashAttribute("test", test);
		} else {
			IAnswerDAO IAnswerDao = new IplmAnswerDAO();

			// Remove old answers
			boolean removeOldAnswers = IAnswerDao.deleteByQuestionId(test.getId());

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
				answer.setAnswersQuestion(test);

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
			boolean isCheck = IQuestionDao.update(test);

			System.out.println("test Id: " + test.getId());

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update test successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update test failed!");

			}
		}

		return "redirect:/admin/tests";
	}

	@RequestMapping(value = { "/test-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {

		ITestDAO ITestDao = new IplmTestDAO();
		boolean isCheck = ITestDao.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record failed because it belongto other table!");
		}

		return "redirect:/admin/tests";
	}

}
