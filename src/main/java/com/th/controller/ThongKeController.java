package com.th.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.entity.ThongKe;
import com.th.repository.ThongKeRepository;
import com.th.service.EmpKhoaHocService;
import com.th.service.EmpService;
import com.th.service.KhoaHocService;
import com.th.service.ThongKeService;

@Controller
public class ThongKeController {
	
	@Autowired
	EmpService empService;

	@Autowired
	KhoaHocService khoaHocService;

	@Autowired
	EmpKhoaHocService empKhoaHocService;
	
	@Autowired
	ThongKeService thongKeService;
	
	@RequestMapping("/thongke/giangvien")
	public String viewThongKe(Model model) {
		// kiem tra lai sau
		List<Emp> listEmp = empService.listbychucvu(2);
//		int i = 0;
//		for(Emp emp: listEmp) {
//			if(emp.getChucvu().getTenchucvu().equals("Học Viên")) {
//				System.out.println(emp.getChucvu().getTenchucvu());
//				listEmp.remove(i);
//			}
//			i++;   
//		}
		
		model.addAttribute("listEmp", listEmp);
		return "show_thongke";
	};
	
	@RequestMapping("/thongke/capnhat/{id}")
	public String xuLyThongKe(@PathVariable(name = "id") int id) { 
		
		List<EmpKhoaHoc> listempKH = empKhoaHocService.listbyemp(id);
		int i=0;
		
		for (EmpKhoaHoc empKHcount: listempKH) {
			i++;
		}
		for(EmpKhoaHoc empKH: listempKH) {
			System.out.println("Id của khóa học");
			System.out.println(empKH.getKhoaHoc().getKhoahoc_id());
			
			List<EmpKhoaHoc> listempKH2 = empKhoaHocService.listbykh(empKH.getKhoaHoc().getKhoahoc_id());
			int j=0;
			for(EmpKhoaHoc empKH2: listempKH2) {
				if(empKH2.getEmp().getChucvu().getTenchucvu().equals("Học Viên")) {
				j++;
				System.out.println("1//////////////////////////////////////////////////////////////////////////");
				}
				System.out.println("2//////////////////////////////////////////////////////////////////////////");
			}
			ThongKe x = new ThongKe();
			//cho id thong ke = id cua empkhoahoc
			x.setIdthongke(empKH.getId());
		    
//		    x.setSolopdangday(i);
			x.setTongsohocvien(j);
//			x.setThoigianthongke(dateobj);
			thongKeService.save(x);
			
			//set cho emkhoahoc = id cua thong ke 
			empKH.setThongKe(thongKeService.get(empKH.getId()));
			empKhoaHocService.save(empKH);
			
			
		}
		Emp y = empService.get(id);
		y.setSolopdangday(i);
		Date dateobj = new Date();
		y.setThoigianthongke(dateobj);
		empService.save(y);
		
		
		return "redirect:/thongke/giangvien";
	}
	
}
