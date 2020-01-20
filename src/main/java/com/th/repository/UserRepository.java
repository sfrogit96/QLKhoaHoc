package com.th.repository;

import org.springframework.data.repository.CrudRepository;

import com.th.entity.User;
 

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);
	
}
