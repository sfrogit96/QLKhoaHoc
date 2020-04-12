package com.th.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "aht_thongke")
public class ThongKe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idthongke;
	
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss a")
//	private Date thoigianthongke;
	private int tongsohocvien;
//	private Integer solopdangday;
//	private int soluongdangday;
	
	public int getIdthongke() {
		return idthongke;
	}
	public void setIdthongke(int idthongke) {
		this.idthongke = idthongke;
	}
	public int getTongsohocvien() {
		return tongsohocvien;
	}
	public void setTongsohocvien(int tongsohocvien) {
		this.tongsohocvien = tongsohocvien;
	}
	
	
	
	public ThongKe() {
		
	}
	
	
}
