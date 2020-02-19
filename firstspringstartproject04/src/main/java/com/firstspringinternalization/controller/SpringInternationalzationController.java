package com.firstspringinternalization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringInternationalzationController {
	
	@GetMapping(value = "/")
	public String view() {
		return "i18n";
	}
}

