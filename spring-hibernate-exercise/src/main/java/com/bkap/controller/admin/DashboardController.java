package com.bkap.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	@RequestMapping(value = { "/admin"}, method = RequestMethod.GET)
	public String index() {
		return "admin/index";
	}
}
