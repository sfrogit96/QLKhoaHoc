package com.th.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.service.EmpKhoaHocService;
import com.th.service.EmpService;
import com.th.service.KhoaHocService;

@Controller
public class EmpKhoaHocController {

	@Autowired
	EmpService empService;
	
	@Autowired
	KhoaHocService khoaHocService;
	
	@Autowired
	EmpKhoaHocService empKhoaHocService;
	
	@RequestMapping("/empkhoahoc/{id}")
	public String viewKhoaHoc(@PathVariable(name = "id") int id, Model model) {
		
		System.out.println("Begin");
//		Date date = new Date(); 
//		KhoaHoc kh = new KhoaHoc("Khoa Hoc Test5", date, date);
		KhoaHoc kh = khoaHocService.get(id);
//		khoaHocService.save(kh);
//		System.out.println(kh.toString());
//		EmpKhoaHoc empKhoaHoc = new EmpKhoaHoc("hello4", kh);
//		System.out.println(empKhoaHoc.toString());
//		EmpKhoaHoc empKhoaHoc2 = new EmpKhoaHoc("hello5", kh);
//		empKhoaHocService.save(empKhoaHoc);
//		empKhoaHocService.save(empKhoaHoc2);
		
//		List<EmpKhoaHoc> empList = empKhoaHocService.findKH(kh);
//		System.out.println(">>>>>");
//		for(EmpKhoaHoc temp: empList) {
//			System.out.println(temp);
//		}
		
//		System.out.println(kh.toString());
		
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
	
//		List<EmpKhoaHoc> empList = empKhoaHocService.findKH(kh);
//		System.out.println(">>>>>");
//		for(EmpKhoaHoc temp: khoahoc) {
//			System.out.println(temp);
//		}
//		khoaHocService.save(kh); 
		model.addAttribute("listmota", khoahoc);
		System.out.println("End");
		return "show_emp_khoahoc";
	}
	@RequestMapping("addempkhoahoc/{id}")
	public String addEmpKhoaHoc (@PathVariable(name = "id") int id, Model model) {
		EmpKhoaHoc empKhoaHoc = new EmpKhoaHoc();
		KhoaHoc kh = khoaHocService.get(id);
		empKhoaHoc.setKhoaHoc(kh);
		model.addAttribute("empKhoaHoc", empKhoaHoc);
		return "add_emp_khoahoc";
	}
	
	@RequestMapping(value = "/saveempkhoahoc", method = RequestMethod.POST)
	public String saveEmpKhoaHoc(@ModelAttribute("empKhoaHoc") EmpKhoaHoc empKhoaHoc) {
		empKhoaHocService.save(empKhoaHoc);
		return "redirect:/khoahoc";
	}
	
	@RequestMapping(value = "/deleteempkhoahoc/{id}", method = RequestMethod.GET)
	public String deleteEmpKhoaHoc(@PathVariable("id") int id) {
		empKhoaHocService.delete(id);
		return "redirect:/khoahoc";
	}
	
	@RequestMapping("/editempkhoahoc/{id}")
	public ModelAndView viewFormKhoaHoc(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_emp_khoahoc");
		EmpKhoaHoc kh = empKhoaHocService.get(id);
		mav.addObject("kh", kh);
		return mav;
	}
	
}