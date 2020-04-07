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
	private int idthongke;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss a")
	private Date thoigianthongke;
	private int tongsohocvien;
	private Integer solopdangday;
	private int soluongdangday;
	
	public int getIdthongke() {
		return idthongke;
	}
	public void setIdthongke(int idthongke) {
		this.idthongke = idthongke;
	}
	public Date getThoigianthongke() {
		return thoigianthongke;
	}
	public void setThoigianthongke(Date thoigianthongke) {
		this.thoigianthongke = thoigianthongke;
	}
	public int getTongsohocvien() {
		return tongsohocvien;
	}
	public void setTongsohocvien(int tongsohocvien) {
		this.tongsohocvien = tongsohocvien;
	}
	
	
	public int getSolopdangday() {
		return solopdangday;
	}
	public void setSolopdangday(int solopdangday) {
		this.solopdangday = solopdangday;
	}
	
	
	public int getSoluongdangday() {
		return soluongdangday;
	}
	public void setSoluongdangday(int soluongdangday) {
		this.soluongdangday = soluongdangday;
	}
	
	
	public ThongKe(int idthongke, Date thoigianthongke, int tongsohocvien, int solopdangday, int soluongdangday) {
		super();
		this.idthongke = idthongke;
		this.thoigianthongke = thoigianthongke;
		this.tongsohocvien = tongsohocvien;
		this.solopdangday = solopdangday;
		this.soluongdangday = soluongdangday;
	}
	public ThongKe() {
		
	}
	
	
}
