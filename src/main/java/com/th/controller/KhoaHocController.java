package com.th.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.th.entity.KhoaHoc;
import com.th.service.KhoaHocService;

@Controller
public class KhoaHocController {

	@Autowired
	private KhoaHocService khoaHocService;
	
	@RequestMapping("/khoahoc")
	public String viewKhoaHoc(Model model) {
		List<KhoaHoc> listKhoaHoc = khoaHocService.listAll();
		model.addAttribute("listKhoaHoc", listKhoaHoc);
		return "show_khoahoc";
	}
	
	@RequestMapping("/editkhoahoc/{id}")
	public ModelAndView viewFormKhoaHoc(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_khoahoc");
		KhoaHoc kh = khoaHocService.get(id);
		mav.addObject("kh", kh);
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveKhoaHoc(@ModelAttribute("kh") KhoaHoc kh) {
		khoaHocService.save(kh);
		return "redirect:/khoahoc";
	}
	
	@RequestMapping(value = "/deletekhoahoc/{id}", method = RequestMethod.GET)
	public String deleteKhoaHoc(@PathVariable("id") int id) {
		khoaHocService.delete(id);
		return "redirect:/khoahoc";
	}
	
	@RequestMapping("/addkhoahoc")
	public String addKhoaHoc(Model model) {
		KhoaHoc kh = new KhoaHoc();
		model.addAttribute("attributekhoahoc", kh);
		return "add_khoahoc";
	}
	
//	@InitBinder
//	 public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
//    {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
//    }
}
