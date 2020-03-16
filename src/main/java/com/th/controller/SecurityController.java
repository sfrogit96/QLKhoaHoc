package com.th.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		if(request.isUserInRole("ROLE_ADMIN")) {
			return "/fragments/layout2";
		}
		return "/fragments/layout";
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
////	user.setRoles(roles);
//		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		return "add_user";
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
		
		
		if(bindingResult.hasErrors()) {
			
			System.out.println("Check 2");
			model.addAttribute("user", user);
			if (user.getId() != 0) {
				return "edit_user";
			}
			return "add_user";
		}
		
			for(Role role: user.getRoles()) {

				if (role == null)
				{
					System.out.println("Check 1");
					User user2 = new User();
					model.addAttribute("user", user2);
					model.addAttribute("userError","Nhập sai vai trò!");
					if (user.getId() != 0) {
						return "edit_user";
					}
					return "add_user";
				}

		}
			
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		return "redirect:/admin";
	}
	
	@RequestMapping("/edituser/{id}")
	public ModelAndView editUser(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_user");
		User user = userService.get(id);
		user.setPassword("");
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
