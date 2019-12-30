package com.th.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.th.entity.Emp;
import com.th.service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@RequestMapping("/emp")
	public String viewEmp(Model model) {
		List<Emp> listEmp = empService.listAll();
		model.addAttribute("listEmp", listEmp);
		return "show_emp";
	}
	
	@RequestMapping("/addemp")
	public String addEmp(Model model) {
		Emp emp = new Emp();
		model.addAttribute("emp", emp);
		return "add_emp";
	}
	
	@RequestMapping(value = "/empsave", method = RequestMethod.POST)
	public String saveEmp(@ModelAttribute("emp") Emp emp) {
		empService.save(emp);
		return "redirect:/emp"; 
	}
	@RequestMapping("/editemp/{id}")
	public ModelAndView editEmp(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_emp");
		Emp emp  = empService.get(id);
		mav.addObject("emp", emp);
		return mav;
	}
	
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String deleteEmp(@PathVariable("id") int id) {
		empService.delete(id);
		return "redirect:/emp";
	}
}
