package com.th.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.validation.Valid;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.service.EmpKhoaHocService;
import com.th.service.EmpService;
import com.th.service.KhoaHocService;
 
//luu y cach dat ten, nen dat mapping => /tenlop/chucnang
@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@Autowired
	KhoaHocService khoaHocService;
	
	@Autowired
	EmpKhoaHocService empKhoaHocService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
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
	
	@PostMapping(value = "/empsave")
	public String saveEmp(@ModelAttribute("emp") @Valid Emp emp, BindingResult bindingResult, Model model) {
		
		
		
		if(bindingResult.hasErrors() || emp.getChucvu().getId()!= 1 || emp.getChucvu().getId()!=2) {
			System.out.println("Error to save emp");
			model.addAttribute("emp",emp);
			if(emp.getId() != 0) {
				if(emp.getChucvu().getId()<1 || emp.getChucvu().getId()>2) {
					System.out.println("Chay vao day");
					System.out.println(emp.getChucvu().getId());
					model.addAttribute("chucvuError","Nhập chức vụ lỗi!");
					return "edit_emp";
				}
				if(bindingResult.hasErrors()){
					return "edit_emp";
				}
				empService.save(emp);
				return "redirect:/emp";
			}
			if(emp.getChucvu().getId()<1 || emp.getChucvu().getId()>2) {
				System.out.println("Chay vao day 2");
				model.addAttribute("chucvuError","Nhập chức vụ lỗi!");
				return "add_emp";
			}
			if(bindingResult.hasErrors()){
				return "add_emp";
			}
		}
		
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
	
	
//	@RequestMapping("/empkhoahoc/{id}")
//	public String viewKhoaHoc(@PathVariable(name = "id") int id, Model model) {
//		
//	    
//		System.out.println("123");
////		Date date = new Date(); 
////		KhoaHoc kh = new KhoaHoc("Khoa Hoc Test5", date, date);
//		KhoaHoc kh = khoaHocService.get(id);
////		khoaHocService.save(kh);
////		System.out.println(kh.toString());
////		EmpKhoaHoc empKhoaHoc = new EmpKhoaHoc("hello4", kh);
////		System.out.println(empKhoaHoc.toString());
////		EmpKhoaHoc empKhoaHoc2 = new EmpKhoaHoc("hello5", kh);
////		empKhoaHocService.save(empKhoaHoc);
////		empKhoaHocService.save(empKhoaHoc2);
//		
////		List<EmpKhoaHoc> empList = empKhoaHocService.findKH(kh);
////		System.out.println(">>>>>");
////		for(EmpKhoaHoc temp: empList) {
////			System.out.println(temp);
////		}
//		
////		System.out.println(kh.toString());
//		
//		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
//	
////		List<EmpKhoaHoc> empList = empKhoaHocService.findKH(kh);
////		System.out.println(">>>>>");
////		for(EmpKhoaHoc temp: khoahoc) {
////			System.out.println(temp);
////		}
////		khoaHocService.save(kh); 
//		model.addAttribute("listmota", khoahoc);
//		System.out.println("456");
//		return "show_emp_khoahoc";
//	}

//	@RequestMapping("/testindex5")
//	public String test() {
//		return "index5";
//	}
//	
//	@RequestMapping("/testindex4")
//	public String test2() {
//		return "index4";
//	}
}
