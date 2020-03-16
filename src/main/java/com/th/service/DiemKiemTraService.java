package com.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.BaiKiemTra;
import com.th.entity.DiemKiemTra;
import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.repository.BaiKiemTraRepository;
import com.th.repository.DiemKiemTraRepository;
import com.th.repository.EmpKhoaHocRepository;
import com.th.repository.EmpRepository;

@Service
public class DiemKiemTraService {
	@Autowired
	private DiemKiemTraRepository diemKiemTraRepository;
	
	public List<DiemKiemTra> listAll(){
		return diemKiemTraRepository.findAll();
	}
	
	public void save(DiemKiemTra emp) {
		diemKiemTraRepository.save(emp);
	}
	
	public DiemKiemTra get(int id) {
		return diemKiemTraRepository.findById(id).get();
	}
	
	public void delete(int id) {
		diemKiemTraRepository.deleteById(id);
	}
}
