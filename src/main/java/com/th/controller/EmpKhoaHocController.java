package com.th.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@Autowired
	ServletContext context;
	
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
	
	
	@RequestMapping("/reportempkhoahoc/{id}")
	public void createPdf(@PathVariable(name = "id") int id, HttpServletRequest request, HttpServletResponse response) {
		 

		KhoaHoc kh = khoaHocService.get(id); 
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
		boolean isFlag = khoaHocService.createPdf(khoahoc, context, request, response);
		
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"employees"+".pdf");
			filedownload(fullPath, response, "employees.pdf");
		} 
	}
	
	@RequestMapping(value="/reportempkhoahoc2/{id}")
	public void createExcel(@PathVariable(name = "id") int id, HttpServletRequest request, HttpServletResponse response) {
		KhoaHoc kh = khoaHocService.get(id);
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
		boolean isFlag = khoaHocService.createExcel(khoahoc, context, request, response);
		
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"employees"+".xls");
			filedownload(fullPath, response, "employees.xls");
		}
		
		
	}
	
	
	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 8192;
		if(file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				response.setContentType("application/pdf; charset=UTF-8");
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1; 
				while((bytesRead = inputStream.read(buffer))!=-1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
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