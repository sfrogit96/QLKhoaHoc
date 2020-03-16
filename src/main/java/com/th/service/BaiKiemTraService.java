package com.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.BaiKiemTra;
import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.repository.BaiKiemTraRepository;
import com.th.repository.EmpKhoaHocRepository;
import com.th.repository.EmpRepository;

@Service
public class BaiKiemTraService {
	@Autowired
	private BaiKiemTraRepository baiKiemTraRepository;
	
	public List<BaiKiemTra> listAll(){
		return baiKiemTraRepository.findAll();
	}
	
	public void save(BaiKiemTra emp) {
		baiKiemTraRepository.save(emp);
	}
	
	public BaiKiemTra get(int id) {
		return baiKiemTraRepository.findById(id).get();
	}
	
	public void delete(int id) {
		baiKiemTraRepository.deleteById(id);
	}
}
