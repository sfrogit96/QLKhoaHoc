package com.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.entity.User;
import com.th.repository.UserRepository;
 

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> listAll(){
		return userRepository.findAll();
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
//	public void savemota(EmpKhoaHoc empKhoaHoc) {
//		khoaHocRepository.save(empKhoaHoc);
//	}
	
	public User get(int id) {
		return userRepository.findById(id).get();
	}
	
	public void delete(int id) {
		userRepository.deleteById(id);
	}
}
