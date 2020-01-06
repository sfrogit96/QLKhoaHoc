package com.th.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.repository.KhoaHocRepository;

@Service
public class KhoaHocService {

	@Autowired
	private KhoaHocRepository khoaHocRepository;
	
	public List<KhoaHoc> listAll(){
		return khoaHocRepository.findAll();
	}
	
	public void save(KhoaHoc khoaHoc) {
		khoaHocRepository.save(khoaHoc);
	}
	
//	public void savemota(EmpKhoaHoc empKhoaHoc) {
//		khoaHocRepository.save(empKhoaHoc);
//	}
	
	public KhoaHoc get(int id) {
		return khoaHocRepository.findById(id).get();
	}
	
	public void delete(int id) {
		 khoaHocRepository.deleteById(id);
	}
}
