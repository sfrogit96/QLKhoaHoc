package com.th.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.th.entity.BaiKiemTra;
import com.th.entity.DiemKiemTra;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.service.BaiKiemTraService;
import com.th.service.DiemKiemTraService;
import com.th.service.EmpKhoaHocService;
import com.th.service.EmpService;
import com.th.service.KhoaHocService;

@Controller
public class BaiKiemTraController {

	@Autowired
	EmpService empService;

	@Autowired
	KhoaHocService khoaHocService;

	@Autowired
	EmpKhoaHocService empKhoaHocService;
	
	@Autowired
	BaiKiemTraService baiKiemTraService;
	
	@Autowired
	DiemKiemTraService diemKiemTraService;
	
	
	@GetMapping("/baikiemtra/{id}/{id2}")
	public String viewDiem(@PathVariable(name = "id") int id, @PathVariable(name = "id2") int id2, Model model) {
		
		KhoaHoc kh = khoaHocService.get(id);
		
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
		
		BaiKiemTra baiKiemTra = baiKiemTraService.get(id2);
		
		List<DiemKiemTra> dkt = baiKiemTra.getDiemKiemTra();
		
//		for(EmpKhoaHoc empKhoaHoc: khoahoc) { 
//			List<DiemKiemTra> dkt = empKhoaHoc.getDiemKiemTra();
//			//show ra
//		}
		model.addAttribute("kh",kh);
		model.addAttribute("listmota", khoahoc);
		
		
		model.addAttribute("baikiemtra", baiKiemTra);
		model.addAttribute("diemkiemtra", dkt); 
		
		
		return "show_diemkiemtra";
	}
	@GetMapping("/baikiemtra/findDkt")
	@ResponseBody
	public DiemKiemTra findOne(Integer id) {
		return diemKiemTraService.get(id);
	}
	
	@PostMapping("/baikiemtra/{id}/{id2}")
	public String savedkt(@PathVariable(name = "id") int id, @PathVariable(name = "id2") int id2, DiemKiemTra d) {
		 
		DiemKiemTra x = diemKiemTraService.get(d.getIddiemkt());
		x.setDiemso(d.getDiemso());
		x.setDanhgia(d.getDanhgia());
		
		diemKiemTraService.save(x);
		
		return "redirect:/baikiemtra/{id}/{id2}";
	}
	
	
	@PostMapping("/savebktonly")
	public String savebktZ(BaiKiemTra b) {
		baiKiemTraService.save(b);
		return "redirect:/baikiemtra";
	}
	
	@GetMapping("/findonebkt")
	@ResponseBody
	public BaiKiemTra findOne(int id) {
		return baiKiemTraService.get(id);
	}
	
	
	@RequestMapping("/baikiemtra")
	public String viewBaiKiemTra(Model model) {
		List<KhoaHoc> listKhoaHoc = khoaHocService.listAll();
		model.addAttribute("listKhoaHoc", listKhoaHoc);
		return "show_baikiemtra";
	}
	
	@RequestMapping("/addbkt/{id}")
	public String addBkt(@PathVariable(name = "id") int id, Model model) {
		KhoaHoc kh = khoaHocService.get(id);
		BaiKiemTra bkt = new BaiKiemTra();
		bkt.setKh(kh);
		model.addAttribute("bkt", bkt);
		return "add_bkt";
	}
	
	@RequestMapping("/deletebaikiemtra/{id}")
	public String deleteBkt(@PathVariable(name = "id") int id) {
		
		baiKiemTraService.delete(id);
		
		return "redirect:/baikiemtra";
	}
	
	@RequestMapping("/savebkt/{id}")
	public String savebkt(@PathVariable(name = "id") int id, @ModelAttribute("bkt") BaiKiemTra bkt) {
		
		KhoaHoc kh = khoaHocService.get(id);
		
		List<EmpKhoaHoc> khoahoc = kh.getEmpKhoaHoc();
		
		baiKiemTraService.save(bkt);
		
		BaiKiemTra bkt2 = new BaiKiemTra();
		
		List<BaiKiemTra> listBkt = kh.getBaiKiemTra();
		
		for(BaiKiemTra bkt3: listBkt) {
			bkt2 = bkt3;
		}
		
		for(EmpKhoaHoc empKhoaHoc: khoahoc) {
			if(empKhoaHoc.getEmp().getChucvu().getId()==1) {
			DiemKiemTra dkt = new DiemKiemTra();
			dkt.setEmpKhoaHoc(empKhoaHoc);
			dkt.setBaikt(bkt2);
			dkt.setDiemso(-1);
			System.out.println("ID empKhoaHoc: "+empKhoaHoc.getId());
			diemKiemTraService.save(dkt);
			}
			//show ra
	    }
		
		System.out.println("ID cua khoa hoc");
		System.out.println(id); 
		
		return "redirect:/baikiemtra";
	}
	
//	@
//	public String addBaiKiemTra() {
//		
//	} 
} 