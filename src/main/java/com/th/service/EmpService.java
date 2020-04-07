package com.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.Emp;
import com.th.entity.KhoaHoc;
import com.th.repository.EmpRepository;

@Service
public class EmpService {
	@Autowired
	private EmpRepository empRepository;
	
	public List<Emp> listbychucvu(int empId){
		return empRepository.findAllByCurrentEmp(empId);
	}
	
	public List<Emp> listAll(){
		return empRepository.findAll();
	}
	
	public void save(Emp emp) {
		empRepository.save(emp);
	}
	
	public Emp get(int id) {
		return empRepository.findById(id).get();
	}
	
	public void delete(int id) {
		empRepository.deleteById(id);
	}
}
