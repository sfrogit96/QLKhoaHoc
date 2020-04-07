package com.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.BaiKiemTra;
import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.entity.ThongKe;
import com.th.repository.BaiKiemTraRepository;
import com.th.repository.EmpKhoaHocRepository;
import com.th.repository.EmpRepository;
import com.th.repository.ThongKeRepository;

@Service
public class ThongKeService {
	@Autowired
	private ThongKeRepository thongKeRepository;
	
	public List<ThongKe> listAll(){
		return thongKeRepository.findAll();
	}
	
	public void save(ThongKe tk) {
		thongKeRepository.save(tk);
	}
	
	public ThongKe get(int id) {
		return thongKeRepository.findById(id).get();
	}
	
	public void delete(int id) {
		thongKeRepository.deleteById(id);
	}
}
