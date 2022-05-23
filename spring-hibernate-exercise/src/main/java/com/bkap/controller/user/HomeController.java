package com.bkap.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public String index() {
		return "user/index";
	}

	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public String test() {
		return "user/index";
	}
}
