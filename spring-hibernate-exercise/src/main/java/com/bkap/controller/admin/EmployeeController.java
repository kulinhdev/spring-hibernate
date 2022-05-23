package com.bkap.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
public class EmployeeController {
	@RequestMapping(value = { "/employees" }, method = RequestMethod.GET)
	public String index(Model model) {
		IEmployeeDAO IEmplDao = new ImplEmployeeDAO();
		List<Employee> listEmployees = IEmplDao.select();

		model.addAttribute("listEmployees", listEmployees);

		return "admin/employee/list";
	}

	@RequestMapping(value = { "/employee-create" }, method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		IEmployeeRoleDAO IRole = new IplmRoleDAO();
		List<EmployeeRole> listRoles = IRole.select();

		Employee employee = new Employee();

		// Check employee exits case redirect update failed!
		if (!model.containsAttribute("employee")) {
			employee.setStatus(true);
			employee.setGender(true);
			model.addAttribute("employee", employee);
		}

		model.addAttribute("listRoles", listRoles);

		return "admin/employee/create";
	}

	@RequestMapping(value = { "/employee-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("employee") Employee e, BindingResult br,
			@RequestParam("fileAvatar") MultipartFile fileAvatar, HttpServletRequest httpRequest, ModelMap model,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.employee", br);

			redirect.addFlashAttribute("employee", e);

			return "redirect:/admin/employee-create";
		} else {
			// Upload file
			if (fileAvatar != null) {
				try {
					String fileName = fileAvatar.getOriginalFilename();
					byte dataFile[] = fileAvatar.getBytes();

					System.out.println("fileName --- " + fileName);

					// Set attribute value
					e.setAvatar(fileName);

					// Get file path
					String realPath = httpRequest.getServletContext().getRealPath("/public/uploads");
//					File fileDestination = new File(realPath + File.separator + fileName);

//					System.out.println("fileDestination --- " + fileDestination);
					
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(realPath + File.separator + fileName)));
					
					stream.write(dataFile);  

//					Files.write(fileDestination.toPath(), dataFile, StandardOpenOption.CREATE);

					System.out.println("Write file success!");
				} catch (IOException exception) {
					System.out.println("Write file error!");
					exception.printStackTrace();
				}
			}

			System.out.println("Employee Object " + e.toString());

			// Insert Data to DB
			IEmployeeDAO IEmplDao = new ImplEmployeeDAO();
			boolean isCheck = IEmplDao.insert(e);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Create new employee successfully!");
			} else {
				redirect.addFlashAttribute("message", "Create new employee failed!");

			}

			return "redirect:/admin/employees";
		}

	}

	@RequestMapping(value = { "/employee-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		IEmployeeRoleDAO IRole = new IplmRoleDAO();
		List<EmployeeRole> listRoles = IRole.select();

		IEmployeeDAO IEmplDao = new ImplEmployeeDAO();
		Employee employee = IEmplDao.detail(id);

		// Check employee exits
		if (!model.containsAttribute("employee")) {
			model.addAttribute("employee", employee);
		}
		model.addAttribute("listRoles", listRoles);

		return "admin/employee/edit";
	}

	@RequestMapping(value = { "/employee-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("employee") Employee e, BindingResult br,
			@RequestParam("fileAvatar") MultipartFile fileAvatar, HttpServletRequest httpRequest,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.employee", br);

			redirect.addFlashAttribute("employee", e);

			return "redirect:/admin/employee-edit/" + e.getId();
		} else {
			// Upload file
			if (fileAvatar != null && !fileAvatar.getOriginalFilename().isEmpty()) {
				try {
					String fileName = fileAvatar.getOriginalFilename();
					byte dataFile[] = fileAvatar.getBytes();

					System.out.println("fileName --- " + fileName);

					// Get file path
					String realPath = httpRequest.getServletContext().getRealPath("/public/uploads");
					File fileDestination = new File(realPath + File.separator + fileName);

					System.out.println("fileDestination --- " + fileDestination);

					String deletePath = realPath + File.separator + e.getAvatar();

					Files.delete(Paths.get(deletePath));

					// Set new attribute value
					e.setAvatar(fileName);

					Files.write(fileDestination.toPath(), dataFile, StandardOpenOption.CREATE);

					System.out.println("Write file success!");
				} catch (IOException exception) {
					System.out.println("Write file error!");
					exception.printStackTrace();
				}
			}

			System.out.println("Employee Object " + e.toString());

			// Update Data to DB
			IEmployeeDAO IEmplDao = new ImplEmployeeDAO();
			boolean isCheck = IEmplDao.update(e);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update employee: " + e.getName() + " successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update employee: " + e.getName() + " failed!");
			}

			return "redirect:/admin/employees";
		}
	}

	@RequestMapping(value = { "/employee-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, ModelMap model, RedirectAttributes redirect) {

		IEmployeeDAO IEmplDao = new ImplEmployeeDAO();
		boolean isCheck = IEmplDao.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " failed!");
		}

		return "redirect:/admin/employees";
	}

}
