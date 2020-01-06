package com.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.repository.EmpKhoaHocRepository;

@Service
public class EmpKhoaHocService {

	@Autowired
	private EmpKhoaHocRepository empKhoaHocRepository;
	
	public void save(EmpKhoaHoc empKhoaHoc) {
		empKhoaHocRepository.save(empKhoaHoc);
	}
	
	public List<EmpKhoaHoc> findKH(KhoaHoc kh){
		return empKhoaHocRepository.findByKhoaHoc(kh);
	}
	
//	public List<EmpKhoaHoc> findAllKH(int id){
//		return empKhoaHocRepository.findAllByCurrentKhoaHoc(id);
//	}
}
