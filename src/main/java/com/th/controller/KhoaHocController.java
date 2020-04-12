package com.th.controller;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.service.EmpKhoaHocService;
import com.th.service.KhoaHocService;

@Controller
public class KhoaHocController {

	@Autowired
	private KhoaHocService khoaHocService;
	
	@Autowired
	private EmpKhoaHocService EmpKhoaHocService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping("/khoahoc")
	public String viewKhoaHoc(Model model) {
		List<KhoaHoc> listKhoaHoc = khoaHocService.listAll();
		model.addAttribute("listKhoaHoc", listKhoaHoc);
		return "show_khoahoc";
	}
	@RequestMapping("/activedkhoahoc")
	public String viewKhoaHoc2(Model model) {
		List<KhoaHoc> listKhoaHoc = khoaHocService.listAll();
		model.addAttribute("listKhoaHoc", listKhoaHoc);
		return "show_khoahoc2";
	}
	
	@RequestMapping("/disabledkhoahoc")
	public String viewKhoaHoc3(Model model) {
		List<KhoaHoc> listKhoaHoc = khoaHocService.listAll();
		model.addAttribute("listKhoaHoc", listKhoaHoc);
		return "show_khoahoc3";
	}
	
	@RequestMapping("/editkhoahoc/{id}")
	public ModelAndView viewFormKhoaHoc(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_khoahoc");
		KhoaHoc kh = khoaHocService.get(id);
		mav.addObject("attributekhoahoc", kh);
		return mav;
	}
	
	@PostMapping(value = "/save")
	public String saveKhoaHoc(@ModelAttribute("attributekhoahoc") @Valid KhoaHoc kh, BindingResult bindingResult, Model model) {
		 
//		System.out.println(kh.getNgaybatdau().compareTo(kh.getNgayketthuc()));
		
		//kh.getNgayketthuc().compareTo(kh.getNgaybatdau())<0
		if(bindingResult.hasErrors() || kh.getNgayketthuc() != null || kh.getNgaybatdau() != null) {
			if(kh.getNgayketthuc() == null || kh.getNgaybatdau() == null) {
				model.addAttribute("attributekhoahoc", kh);
				return "add_khoahoc";
			}
			Date startDate = kh.getNgaybatdau();
			Date endDate = kh.getNgayketthuc();
			LocalDateTime d1 = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
			LocalDateTime d2 = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
			if(Duration.between(d1, d2).toDays()<0) {
			model.addAttribute("dateError","Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu!");
			model.addAttribute("attributekhoahoc",kh);
			return "add_khoahoc";
			}
			if(bindingResult.hasErrors()) {
				model.addAttribute("attributekhoahoc",kh);
				return "edit_khoahoc";
				}
		}
		
		kh.setStatus(true);
		khoaHocService.save(kh);
		return "redirect:/khoahoc";
	}
	
	@PostMapping(value = "/save2")
	public String saveKhoaHoc2(@ModelAttribute("attributekhoahoc") @Valid KhoaHoc kh, BindingResult bindingResult, Model model) {
		 
//		System.out.println(kh.getNgaybatdau().compareTo(kh.getNgayketthuc()));
		
		//kh.getNgayketthuc().compareTo(kh.getNgaybatdau())<0
		if(bindingResult.hasErrors() || kh.getNgayketthuc() != null || kh.getNgaybatdau() != null) {
			if(kh.getNgayketthuc() == null || kh.getNgaybatdau() == null) {
				model.addAttribute("attributekhoahoc", kh);
				return "edit_khoahoc";
			}

			Date startDate = kh.getNgaybatdau();
			Date endDate = kh.getNgayketthuc();
			LocalDateTime d1 = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
			LocalDateTime d2 = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
			if(Duration.between(d1, d2).toDays()<0) {
			model.addAttribute("dateError","Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu!");
			model.addAttribute("attributekhoahoc",kh);
			return "edit_khoahoc";
			}
			if(bindingResult.hasErrors()) {
			model.addAttribute("attributekhoahoc",kh);
			return "edit_khoahoc";
			}
		}
		kh.setStatus(true);
		khoaHocService.save(kh);
		return "redirect:/khoahoc";
	}
	
	@RequestMapping(value = "/changestatus/{id}", method = RequestMethod.GET)
	public String changeStatus(@PathVariable("id") int id) {
		KhoaHoc kh = khoaHocService.get(id);
		if(kh.isStatus()==true) {
			kh.setStatus(false);
		}
		else kh.setStatus(true);
		khoaHocService.save(kh);
		return "redirect:/khoahoc";
	}
	
	@RequestMapping(value = "/deletekhoahoc/{id}", method = RequestMethod.GET)
	public String deleteKhoaHoc(@PathVariable("id") int id, Model model) {
		
		KhoaHoc kh = khoaHocService.get(id);
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
		
		if(khoahoc.isEmpty()) {
			model.addAttribute("error1", "no");
			khoaHocService.delete(id);
			return "redirect:/khoahoc";
		}
		else {
			List<KhoaHoc> listKhoaHoc = khoaHocService.listAll();
			model.addAttribute("listKhoaHoc", listKhoaHoc);
			model.addAttribute("id", id);
			model.addAttribute("error2", "Khóa học vẫn còn học viên, không thể xóa!");
			return "show_khoahoc";
		}
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
