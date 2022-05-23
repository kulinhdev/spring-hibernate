package com.bkap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	@RequestMapping(value = { "/home", "/" })
	public String index() {
		return "index";
	}

	@RequestMapping("calculator/{p1}/{p2}")
	public String calculator(@PathVariable("p1") int p1, @PathVariable("p2") int p2, Model m) {

		String sum = "Kq: " + p1 + " + " + p2 + " = " + (p1 + p2);

		m.addAttribute("sum", sum);

		return "calculator";
	}

}
