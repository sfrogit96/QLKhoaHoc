package com.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.repository.EmpKhoaHocRepository;
import com.th.repository.EmpRepository;

@Service
public class EmpKhoaHocService {
	@Autowired
	private EmpKhoaHocRepository empKhoaHocRepository;
	
	public List<EmpKhoaHoc> listbyemp(int empId){
		return empKhoaHocRepository.findAllByCurrentEmpKhoaHoc(empId);
	}
	
	public List<EmpKhoaHoc> listbykh(int khId){
		return empKhoaHocRepository.findAllByCurrentEmpKhoaHoc2(khId);
	}
	
	public List<EmpKhoaHoc> listAll(){
		return empKhoaHocRepository.findAll();
	}
	
	public void save(EmpKhoaHoc emp) {
		empKhoaHocRepository.save(emp);
	}
	
	public EmpKhoaHoc get(int id) {
		return empKhoaHocRepository.findById(id).get();
	}
	
	public void delete(int id) {
		empKhoaHocRepository.deleteById(id);
	}
}
