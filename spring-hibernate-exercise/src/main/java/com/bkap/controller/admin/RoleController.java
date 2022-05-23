package com.bkap.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.IEmployeeDAO;
import dao.IEmployeeRoleDAO;
import dao.ImplEmployeeDAO;
import dao.IplmRoleDAO;
import entity.Employee;
import entity.EmployeeRole;

@Controller
@RequestMapping(value = { "/admin" })
public class RoleController {
	@RequestMapping(value = { "/roles" }, method = RequestMethod.GET)
	public String index(Model model) {

		IEmployeeRoleDAO IEmRoleDAO = new IplmRoleDAO();
		List<EmployeeRole> roles = IEmRoleDAO.select();
		model.addAttribute("roles", roles);

		EmployeeRole role = new EmployeeRole();

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("role")) {
			role.setStatus(true);
			model.addAttribute("role", role);
		}

		return "admin/role/list";
	}

	@RequestMapping(value = { "/role-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("role") EmployeeRole role, BindingResult br,
			RedirectAttributes redirect) {
		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.role", br);

			redirect.addFlashAttribute("role", role);
		} else {
			IEmployeeRoleDAO IEmRoleDAO = new IplmRoleDAO();
			boolean isCheck = IEmRoleDAO.insert(role);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Create new role successfully!");
			} else {
				redirect.addFlashAttribute("message", "Create new role failed!");

			}
		}

		return "redirect:/admin/roles";
	}

	@RequestMapping(value = { "/role-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		IEmployeeRoleDAO IEmRoleDAO = new IplmRoleDAO();
		List<EmployeeRole> roles = IEmRoleDAO.select();

		model.addAttribute("roles", roles);

		EmployeeRole role = IEmRoleDAO.detail(id);

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("role")) {
			model.addAttribute("role", role);
		}

		return "admin/role/edit";
	}

	@RequestMapping(value = { "/role-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("role") EmployeeRole role, BindingResult br,
			HttpServletRequest httpRequest, RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.role", br);
		} else {
			IEmployeeRoleDAO IEmRoleDAO = new IplmRoleDAO();
			boolean isCheck = IEmRoleDAO.update(role);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update role successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update role failed!");

			}
		}

		redirect.addFlashAttribute("role", role);

		// Return back after handle request.
		return "redirect:/admin/role-edit/" + role.getId();
	}

	@RequestMapping(value = { "/role-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {

		IEmployeeRoleDAO IEmRoleDAO = new IplmRoleDAO();
		boolean isCheck = IEmRoleDAO.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " failed!");
		}

		// Check action from ?

		return "redirect:/admin/roles";
	}

}
