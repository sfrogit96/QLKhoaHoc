package com.th.repository;

import org.springframework.data.repository.CrudRepository;

import com.th.entity.Role;
 
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByName(String name);
	
}
