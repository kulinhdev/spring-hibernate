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

import dao.IClassDAO;
import dao.IEmployeeDAO;
import dao.ISubjectDAO;
import dao.ImplClassDAO;
import dao.ImplEmployeeDAO;
import dao.ImplSubjectDAO;
import entity.Class;
import entity.Employee;
import entity.Subject;

@Controller
@RequestMapping(value = { "/admin" })
public class SubjectController {
	@RequestMapping(value = { "/subjects" }, method = RequestMethod.GET)
	public String index(Model model) {
		ISubjectDAO ISubjectDao = new ImplSubjectDAO();
		List<Subject> listSubjects = ISubjectDao.select();

		model.addAttribute("listSubjects", listSubjects);

		return "admin/subject/list";
	}

	@RequestMapping(value = { "/subject-create" }, method = RequestMethod.GET)
	public String create(Model model) {
		IClassDAO IClassDao = new ImplClassDAO();
		List<Class> listClasses = IClassDao.select();

		model.addAttribute("listClasses", listClasses);

		Subject subject = new Subject();

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("subject")) {
			subject.setStatus(true);
			model.addAttribute("subject", subject);
		}

		return "admin/subject/create";
	}

	@RequestMapping(value = { "/subject-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("subject") Subject subject, BindingResult br,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.subject", br);

			redirect.addFlashAttribute("subject", subject);

			return "redirect:/admin/subject-create";
		} else {
			// Insert Data to DB
			ISubjectDAO ISubjectDao = new ImplSubjectDAO();
			boolean isCheck = ISubjectDao.insert(subject);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update subject successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update subject failed!");

			}

			return "redirect:/admin/subjects";
		}
	}

	@RequestMapping(value = { "/subject-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		IClassDAO IClassDao = new ImplClassDAO();
		List<Class> listClasses = IClassDao.select();

		model.addAttribute("listClasses", listClasses);

		ISubjectDAO ISubjectDao = new ImplSubjectDAO();

		Subject subject = ISubjectDao.detail(id);

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("subject")) {
			model.addAttribute("subject", subject);
		}

		return "admin/subject/edit";
	}

	@RequestMapping(value = { "/subject-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("subject") Subject subject, BindingResult br,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.subject", br);

			redirect.addFlashAttribute("subject", subject);

			return "redirect:/admin/subject-edit/" + subject.getId();
		} else {
			// Update Data to DB
			ISubjectDAO ISubjectDao = new ImplSubjectDAO();
			boolean isCheck = ISubjectDao.update(subject);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update subject successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update subject failed!");

			}

			return "redirect:/admin/subjects";
		}
	}

	@RequestMapping(value = { "/subject-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {

		ISubjectDAO ISubjectDao = new ImplSubjectDAO();
		boolean isCheck = ISubjectDao.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " failed!");
		}

		return "redirect:/admin/subjects";
	}

}
