package com.bkap.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.ILevelDAO;
import dao.IplmLevelDAO;
import entity.QuestionLevel;

@Controller
@RequestMapping(value = { "/admin" })
public class QuestionLevelController {
	@RequestMapping(value = { "/question-levels" }, method = RequestMethod.GET)
	public String index(Model model) {
		ILevelDAO ILevelDao = new IplmLevelDAO();
		List<QuestionLevel> levels = ILevelDao.select();

		model.addAttribute("levels", levels);

		QuestionLevel level = new QuestionLevel();

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("level")) {
			level.setStatus(true);
			model.addAttribute("level", level);
		}

		return "admin/level/list";
	}

	@RequestMapping(value = { "/level-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("level") QuestionLevel level, BindingResult br,
			RedirectAttributes redirect) {
		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.level", br);

			redirect.addFlashAttribute("level", level);
		} else {
			ILevelDAO ILevelDao = new IplmLevelDAO();
			boolean isCheck = ILevelDao.insert(level);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Create new level successfully!");
			} else {
				redirect.addFlashAttribute("message", "Create new level failed!");

			}
		}

		return "redirect:/admin/question-levels";
	}

	@RequestMapping(value = { "/level-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		ILevelDAO ILevelDao = new IplmLevelDAO();
		List<QuestionLevel> levels = ILevelDao.select();

		model.addAttribute("levels", levels);

		QuestionLevel level = ILevelDao.detail(id);

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("level")) {
			model.addAttribute("level", level);
		}

		return "admin/level/edit";
	}

	@RequestMapping(value = { "/level-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("level") QuestionLevel level, BindingResult br,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.level", br);

			redirect.addFlashAttribute("level", level);
		} else {
			ILevelDAO ILevelDao = new IplmLevelDAO();
			boolean isCheck = ILevelDao.update(level);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update level successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update level failed!");

			}
		}

		return "redirect:/admin/question-levels";
	}

	@RequestMapping(value = { "/level-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {

		ILevelDAO ILevelDao = new IplmLevelDAO();
		boolean isCheck = ILevelDao.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " failed!");
		}

		return "redirect:/admin/question-levels";
	}

}
