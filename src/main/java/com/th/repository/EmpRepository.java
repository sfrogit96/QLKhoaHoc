package com.th.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.th.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	@Query("FROM Emp e where e.chucvu.id = :empId")
	List<Emp> findAllByCurrentEmp(@Param("empId") int empId);
	
}
