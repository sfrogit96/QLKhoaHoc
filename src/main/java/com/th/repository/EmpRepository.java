package com.th.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.th.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

}
