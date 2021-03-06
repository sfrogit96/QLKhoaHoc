package com.th.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.th.entity.Emp;
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;

public interface EmpKhoaHocRepository extends JpaRepository<EmpKhoaHoc, Integer> {
	List<EmpKhoaHoc> findByKhoaHoc(KhoaHoc khoaHoc);
	
//	@Query("FROM EmpKhoaHoc g where g.khoaHoc.id = :id")
//	List<EmpKhoaHoc> findAllByCurrentKhoaHoc(@Param("id") int id);
//	
	

	@Query("FROM EmpKhoaHoc e where e.emp.id = :empId")
	List<EmpKhoaHoc> findAllByCurrentEmpKhoaHoc(@Param("empId") int empId);
	
	@Query("FROM EmpKhoaHoc e where e.khoaHoc.khoahoc_id = :khId")
	List<EmpKhoaHoc> findAllByCurrentEmpKhoaHoc2(@Param("khId") int khId);
	
 }
