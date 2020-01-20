package com.th.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/admin") 
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	@GetMapping("/loginPage") 
	public String getLogin() {
		return "loginPage";
	}
	@GetMapping("/testphanquyen")
	@ResponseBody
	public String testPhanQuyen() {
		return "Hi";
	}
}
