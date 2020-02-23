package com.th.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping("/empkhoahoc/{id}")
	public String viewKhoaHoc(@PathVariable(name = "id") int id, Model model) {

		KhoaHoc kh = khoaHocService.get(id);

		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();

		model.addAttribute("listmota", khoahoc);

		return "show_emp_khoahoc";
	}

	@RequestMapping("/reportempkhoahoc/{id}")
	public void createPdf(@PathVariable(name = "id") int id, HttpServletRequest request, HttpServletResponse response) {

		KhoaHoc kh = khoaHocService.get(id);
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
		boolean isFlag = khoaHocService.createPdf(khoahoc, kh, context, request, response);

		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "employees" + ".pdf");
			filedownload(fullPath, response, "employees.pdf");
		}
	}

	@RequestMapping(value = "/reportempkhoahoc2/{id}")
	public void createExcel(@PathVariable(name = "id") int id, HttpServletRequest request,
			HttpServletResponse response) {
		KhoaHoc kh = khoaHocService.get(id);
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
		boolean isFlag = khoaHocService.createExcel(khoahoc, context, request, response);

		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "employees" + ".xls");
			filedownload(fullPath, response, "employees.xls");
		}

	}

	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 8192;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				response.setContentType("application/pdf; charset=UTF-8");
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
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
	public String addEmpKhoaHoc(@PathVariable(name = "id") int id, Model model) {

		EmpKhoaHoc empKhoaHoc = new EmpKhoaHoc();

		KhoaHoc kh = khoaHocService.get(id);

		List<Emp> empList = empService.listAll();
		empKhoaHoc.setKhoaHoc(kh);
		model.addAttribute("empKhoaHoc", empKhoaHoc);
		model.addAttribute("empList", empList);
		return "add_emp_khoahoc";
	}

	// Neu thay number = id => lay gia tri id cua EmpKhoaHoc
	@PostMapping(value = "/saveempkhoahoc/{number}")
	public String saveEmpKhoaHoc(@PathVariable(name = "number") int id,
			@ModelAttribute("empKhoaHoc") @Valid EmpKhoaHoc empKhoaHoc, BindingResult bindingResult, Model model)
			throws ParseException {

// 		Convert kieu date dai => simple date format
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		// Lay het tat ca hoc vien cua khoa hoc do
		KhoaHoc khID = khoaHocService.get(empKhoaHoc.getKhoaHoc().getKhoahoc_id());
		List<EmpKhoaHoc> listhocvien = khID.getEmpKhoaHoc();

		for (EmpKhoaHoc hv : listhocvien) {
			// Kiem tra ID => add or edit
			if (empKhoaHoc.getId() == hv.getId()) {
				break;
			}
			// info
//			System.out.println(empKhoaHoc.getEmp().getId());
//			System.out.println(hv.getEmp().getId());

			if (empKhoaHoc.getEmp().getId() == hv.getEmp().getId()) {

				model.addAttribute("errorEmp", "Người chọn đã có tên trong danh sách!");
				model.addAttribute("empKhoaHoc", empKhoaHoc);
				List<Emp> empList = empService.listAll();
				model.addAttribute("empList", empList);

				return "add_emp_khoahoc";
			}
		}


		if (bindingResult.hasErrors()) {
			if (empKhoaHoc.getThoigianbatdau() == null && empKhoaHoc.getThoigianketthuc() == null) {
				empKhoaHoc.setThoigianbatdau(khID.getNgaybatdau());
				empKhoaHoc.setThoigianketthuc(khID.getNgayketthuc());
				empKhoaHocService.save(empKhoaHoc);
				return "redirect:/khoahoc";
			} else {
			model.addAttribute("empKhoaHoc", empKhoaHoc);
			List<Emp> empList = empService.listAll();
			model.addAttribute("empList", empList);

			return "add_emp_khoahoc";
			}
		}

		KhoaHoc kh = khoaHocService.get(id);

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = sdf.parse(kh.getNgaybatdau().toString());
		Date startDate2 = empKhoaHoc.getThoigianbatdau();
		Date endDate = sdf.parse(kh.getNgayketthuc().toString());
		Date endDate2 = empKhoaHoc.getThoigianketthuc();

		LocalDateTime d1 = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
		LocalDateTime d2 = LocalDateTime.ofInstant(startDate2.toInstant(), ZoneId.systemDefault());
		LocalDateTime d3 = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
		LocalDateTime d4 = LocalDateTime.ofInstant(endDate2.toInstant(), ZoneId.systemDefault());

		if (Duration.between(d1, d2).toDays() < 0 || Duration.between(d3, d4).toDays() > 0) {
			model.addAttribute("dateError1", "Ngày bắt đầu phải lớn hơn ngày bắt đầu của khóa học!");
			model.addAttribute("dateError2", "Ngày kết thúc phải nhỏ hơn ngày kết thúc của khóa học!");
			model.addAttribute("empKhoaHoc", empKhoaHoc);

			List<Emp> empList = empService.listAll();
			model.addAttribute("empList", empList);

			return "add_emp_khoahoc";
		}
		empKhoaHocService.save(empKhoaHoc);
		return "redirect:/khoahoc";
	}

	@RequestMapping(value = "/deleteempkhoahoc/{id}", method = RequestMethod.GET)
	public String deleteEmpKhoaHoc(@PathVariable("id") int id) {
		empKhoaHocService.delete(id);
		return "redirect:/khoahoc";
	}

	@RequestMapping("/editempkhoahoc/{id}")
	public ModelAndView viewFormKhoaHoc(@PathVariable(name = "id") int id, Model model) {
		ModelAndView mav = new ModelAndView("edit_emp_khoahoc");
		EmpKhoaHoc kh = empKhoaHocService.get(id);
		
		List<Emp> empList = empService.listAll();
		model.addAttribute("empList", empList);
		mav.addObject("kh", kh);
		return mav;
	}
}