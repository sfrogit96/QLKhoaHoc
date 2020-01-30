package com.th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.th.entity.User;
 

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
}
