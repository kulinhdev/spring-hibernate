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
import dao.ImplClassDAO;
import dao.ImplEmployeeDAO;
import entity.Class;
import entity.Employee;

@Controller
@RequestMapping(value = { "/admin" })
public class ClassController {
	@RequestMapping(value = { "/classes" }, method = RequestMethod.GET)
	public String index(Model model) {
		IClassDAO IClassDao = new ImplClassDAO();
		List<Class> listClasses = IClassDao.select();

		model.addAttribute("listClasses", listClasses);

		return "admin/class/list";
	}

	@RequestMapping(value = { "/class-create" }, method = RequestMethod.GET)
	public String create(Model model) {
		IEmployeeDAO IEmplDao = new ImplEmployeeDAO();
		List<Employee> listEmployees = IEmplDao.select();

		Class aClass = new Class();

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("aClass")) {
			aClass.setStatus(true);
			model.addAttribute("aClass", aClass);
		}

		model.addAttribute("listEmployees", listEmployees);

		return "admin/class/create";
	}

	@RequestMapping(value = { "/class-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("aClass") Class aClass, BindingResult br, RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.aClass", br);

			redirect.addFlashAttribute("aClass", aClass);

			return "redirect:/admin/class-create";
		} else {
			// Insert Data to DB
			IClassDAO IClassDao = new ImplClassDAO();
			boolean isCheck = IClassDao.insert(aClass);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update class successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update class failed!");

			}

			return "redirect:/admin/classes";
		}
	}

	@RequestMapping(value = { "/class-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		IEmployeeDAO IEmplDao = new ImplEmployeeDAO();
		List<Employee> listEmployees = IEmplDao.select();

		IClassDAO IClassDao = new ImplClassDAO();

		Class aClass = IClassDao.detail(id);

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("aClass")) {
			model.addAttribute("aClass", aClass);
		}

		model.addAttribute("listEmployees", listEmployees);

		return "admin/class/edit";
	}

	@RequestMapping(value = { "/class-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("aClass") Class aClass, BindingResult br, RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.aClass", br);

			redirect.addFlashAttribute("aClass", aClass);

			return "redirect:/admin/class-edit/" + aClass.getId();
		} else {
			// Update Data to DB
			IClassDAO IClassDao = new ImplClassDAO();
			boolean isCheck = IClassDao.update(aClass);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update class successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update class failed!");

			}

			return "redirect:/admin/classes";
		}
	}

	@RequestMapping(value = { "/class-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {

		IClassDAO IClassDao = new ImplClassDAO();
		boolean isCheck = IClassDao.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " failed!");
		}

		return "redirect:/admin/classes";
	}

}
