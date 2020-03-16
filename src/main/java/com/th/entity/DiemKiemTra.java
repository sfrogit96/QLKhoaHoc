package com.th.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "aht_diemkiemtra")
public class DiemKiemTra {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iddiemkt;
	private float diemso;
	private String danhgia;
	
	 
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aht_diem_empkhoahoc")
	private EmpKhoaHoc empKhoaHoc;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aht_baikt_diemkt")
	private BaiKiemTra baikt;
	
	public int getIddiemkt() {
		return iddiemkt;
	}
	
	public void setIddiemkt(int iddiemkt) {
		this.iddiemkt = iddiemkt;
	}

	public float getDiemso() {
		return diemso;
	}
	
	public void setDiemso(float diemso) {
		this.diemso = diemso;
	}
	public String getDanhgia() {
		return danhgia;
	}
	public void setDanhgia(String danhgia) {
		this.danhgia = danhgia;
	}
	public EmpKhoaHoc getEmpKhoaHoc() {
		return empKhoaHoc;
	}
	public void setEmpKhoaHoc(EmpKhoaHoc empKhoaHoc) {
		this.empKhoaHoc = empKhoaHoc;
	}
 

	public BaiKiemTra getBaikt() {
		return baikt;
	}
	public void setBaikt(BaiKiemTra baikt) {
		this.baikt = baikt;
	} 
}
