package com.th.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


//@Entity
//@Table(name = "aht_hocvien_khoahoc")
public class EmpKhoaHoc {
	private int id; 
	private Date thoigianbatdau;
	private Date thoigianketthuc; 
	private String mota;
	private Emp emp;
	private KhoaHoc khoaHoc;
	
	
	
}
