package com.th.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.th.entity.Role;
import com.th.entity.User;
import com.th.repository.RoleRepository;
import com.th.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		if(request.isUserInRole("ROLE_ADMIN")) {
			return "admin";
		} 
		return "show_khoahoc";
	}
	
	@GetMapping("/admin") 
	public String admin(Model model) {
		List<User> listUser = userService.listAll();
		model.addAttribute("listUser", listUser);
		return "admin";
	}
	
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/admin";
	}
	
	@RequestMapping("/adduser")
	public String addUser(Model model) {
		User user = new User();
//		HashSet<Role> roles = new HashSet<>();
//		roles.add(roleRepository.findByName("ROLE_ADMIN"));
//		roles.add(roleRepository.findByName("ROLE_MEMBER"));
////		user.setRoles(roles);
//		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
	
		return "add_user";
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		return "redirect:/admin";
	}
	
	@RequestMapping("/edituser/{id}")
	public ModelAndView editUser(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_user");
		User user = userService.get(id);
		mav.addObject("user", user);
		return mav;
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
