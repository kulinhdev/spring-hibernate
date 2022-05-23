package com.bkap.controller.admin;

import java.io.File;
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

import dao.IClassDAO;
import dao.IStudentDAO;
import dao.ImplClassDAO;
import dao.ImplStudentDAO;
import entity.Class;
import entity.Student;

@Controller
@RequestMapping(value = { "/admin" })
public class StudentController {
	@RequestMapping(value = { "/students" }, method = RequestMethod.GET)
	public String index(Model model) {
		IStudentDAO IEmplDao = new ImplStudentDAO();
		List<Student> listStudents = IEmplDao.select();

		model.addAttribute("listStudents", listStudents);

		return "admin/student/list";
	}

	@RequestMapping(value = { "/student-create" }, method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		IClassDAO IClassDao = new ImplClassDAO();
		List<Class> listClasses = IClassDao.select();

		Student student = new Student();

		// Check student exits case redirect update failed!
		if (!model.containsAttribute("student")) {
			student.setStatus(true);
			student.setGender(true);
			model.addAttribute("student", student);
		}

		model.addAttribute("listClasses", listClasses);

		return "admin/student/create";
	}

	@RequestMapping(value = { "/student-insert" }, method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("student") Student student, BindingResult br,
			@RequestParam("fileAvatar") MultipartFile fileAvatar, HttpServletRequest httpRequest,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.student", br);

			redirect.addFlashAttribute("student", student);

			return "redirect:/admin/student-create";
		} else {
			// Upload file
			if (fileAvatar != null) {
				try {
					String fileName = fileAvatar.getOriginalFilename();
					byte dataFile[] = fileAvatar.getBytes();

					System.out.println("fileName --- " + fileName);

					// Set attribute value
					student.setAvatar(fileName);

					// Get file path
					String realPath = httpRequest.getServletContext().getRealPath("/public/uploads");
					File fileDestination = new File(realPath + File.separator + fileName);

					System.out.println("fileDestination --- " + fileDestination);

					Files.write(fileDestination.toPath(), dataFile, StandardOpenOption.CREATE);

					System.out.println("Write file success!");
				} catch (IOException exception) {
					System.out.println("Write file error!");
					exception.printStackTrace();
				}
			}

			System.out.println("student Object " + student.toString());

			// Insert Data to DB
			IStudentDAO IStudentDao = new ImplStudentDAO();
			boolean isCheck = IStudentDao.insert(student);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Create new student successfully!");
			} else {
				redirect.addFlashAttribute("message", "Create new student failed!");

			}

			return "redirect:/admin/students";
		}

	}

	@RequestMapping(value = { "/student-edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		IClassDAO IClassDao = new ImplClassDAO();
		List<Class> listClasses = IClassDao.select();

		IStudentDAO IStudentDao = new ImplStudentDAO();

		Student student = IStudentDao.detail(id);

		// Check student exits
		if (!model.containsAttribute("student")) {
			model.addAttribute("student", student);
		}

		model.addAttribute("listClasses", listClasses);

		return "admin/student/edit";
	}

	@RequestMapping(value = { "/student-update" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("student") Student student, BindingResult br,
			@RequestParam("fileAvatar") MultipartFile fileAvatar, HttpServletRequest httpRequest,
			RedirectAttributes redirect) {

		if (br.hasErrors()) {
			// Pass binding errors
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.student", br);

			redirect.addFlashAttribute("student", student);

			return "redirect:/admin/student-edit/" + student.getId();
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

					// Remove old image
					String deletePath = realPath + File.separator + student.getAvatar();

					Files.delete(Paths.get(deletePath));

					// Set new attribute value
					student.setAvatar(fileName);

					Files.write(fileDestination.toPath(), dataFile, StandardOpenOption.CREATE);

					System.out.println("Write file success!");
				} catch (IOException exception) {
					System.out.println("Write file error!");
					exception.printStackTrace();
				}
			}

			System.out.println("student Object " + student.toString());

			// Update Data to DB
			IStudentDAO IStudentDao = new ImplStudentDAO();
			boolean isCheck = IStudentDao.update(student);

			if (isCheck) {
				redirect.addFlashAttribute("message", "Update student: " + student.getName() + " successfully!");
			} else {
				redirect.addFlashAttribute("message", "Update student: " + student.getName() + " failed!");
			}

			return "redirect:/admin/students";
		}
	}

	@RequestMapping(value = { "/student-delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, ModelMap model, RedirectAttributes redirect) {

		IStudentDAO IStudentDao = new ImplStudentDAO();
		boolean isCheck = IStudentDao.delete(id);

		if (isCheck) {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " successfully!");
		} else {
			redirect.addFlashAttribute("message", "Delete record with id: " + id + " failed!");
		}

		return "redirect:/admin/students";
	}

}
