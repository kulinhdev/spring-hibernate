package com.bkap.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dao.IPersonDAO;
import dao.ImplPersonDAO;
import entity.Person;

@Controller
public class PersonController {
	@RequestMapping(value = "/people")
	public String getList(@RequestParam(name = "searchName", required = false) String searchName, Model m) {
		IPersonDAO iPerson = new ImplPersonDAO();
		List<Person> listPeople = null;

		if (searchName != null && !searchName.isEmpty()) {
			listPeople = iPerson.search(searchName);

			m.addAttribute("searchName", searchName);
		} else {
			listPeople = iPerson.select();
		}

		m.addAttribute("listPeople", listPeople);

		return "list";
	}

//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public String search(@RequestParam("searchName") String searchName, Model m) {
//
//		System.out.println(searchName);
//
//		IPersonDAO iPerson = new ImplPersonDAO();
//
//		List<Person> listPeople = iPerson.search(searchName);
//
//		m.addAttribute("listPeople", listPeople);
//
//		m.addAttribute("searchName", searchName);
//
//		return "list";
//	}

	@RequestMapping(value = "/add")
	public String add(Model m) {
		Person p = new Person();
		m.addAttribute("person", p);

		// Set default gender
		p.setGender(true);

		return "add";

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("person") Person p, BindingResult br,
			@RequestParam("fileAvatar") MultipartFile fileAvatar, HttpServletRequest httpRequest, Model m) {
		m.addAttribute("person", p);

		if (br.hasErrors()) {
			return "add";
		} else {
			// Upload file
			if (fileAvatar != null) {
				try {
					String fileName = fileAvatar.getOriginalFilename();
					byte dataFile[] = fileAvatar.getBytes();

					// Set attribute value
					p.setAvatar(fileName);

					// Get file path
					String realPath = httpRequest.getServletContext().getRealPath("public/uploads");
					File fileDestination = new File(realPath + File.separator + fileName);

					System.out.println("fileDestination --- " + fileDestination);

					Files.write(fileDestination.toPath(), dataFile, StandardOpenOption.CREATE);
				} catch (IOException e) {
					System.out.println("Read file error!");
					e.printStackTrace();
				}
			}

			// Insert Data to DB
			IPersonDAO iPerson = new ImplPersonDAO();
			boolean isCheck = iPerson.insert(p);

			if (isCheck) {
				m.addAttribute("message", "Create person success!");
			} else {
				m.addAttribute("message", "Create person failed!");
			}

			return "info";
		}
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") int id, Model m) {

		IPersonDAO iPerson = new ImplPersonDAO();
		Person p = iPerson.detail(id);
		m.addAttribute("person", p);

		// Set default gender
		p.setGender(true);

		return "edit";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("person") Person p, BindingResult br,
			@RequestParam("fileAvatar") MultipartFile fileAvatar, HttpServletRequest httpRequest, Model m) {
		m.addAttribute("person", p);

		if (br.hasErrors()) {
			return "edit";
		} else {
			// Upload file
			if (fileAvatar != null) {
				try {
					String fileName = fileAvatar.getOriginalFilename();
					byte dataFile[] = fileAvatar.getBytes();

					// Set attribute value
					p.setAvatar(fileName);

					// Get file path
					String realPath = httpRequest.getServletContext().getRealPath("public/uploads");
					File fileDestination = new File(realPath + File.separator + fileName);

					System.out.println("fileDestination --- " + fileDestination);

					Files.write(fileDestination.toPath(), dataFile, StandardOpenOption.CREATE);
				} catch (IOException e) {
					System.out.println("Read file error!");
					e.printStackTrace();
				}
			}

			IPersonDAO iPerson = new ImplPersonDAO();
			boolean isCheck = iPerson.update(p);

			if (isCheck) {
				m.addAttribute("message", "Update person success!");
				return "info";
			} else {
				m.addAttribute("message", "Update person failed!");
				return "edit";
			}
		}
	}

	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable("id") int id, Model m) {

		IPersonDAO iPerson = new ImplPersonDAO();
		Person p = iPerson.detail(id);
		m.addAttribute("person", p);

		// Set default gender
		p.setGender(true);

		return "info";

	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") int id, Model m) {

		IPersonDAO iPerson = new ImplPersonDAO();
		boolean isCheck = iPerson.delete(id);

		if (isCheck) {
			m.addAttribute("message", "Delete person success!");
		} else {
			m.addAttribute("message", "Delete person failed!");
		}

		List<Person> listPeople = iPerson.select();

		m.addAttribute("listPeople", listPeople);

		return "list";
	}

}
