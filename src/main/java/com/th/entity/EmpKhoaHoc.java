package com.th.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "aht_hocvien_khoahoc")
public class EmpKhoaHoc {
	private int id; 
	private String mota;
	private KhoaHoc khoaHoc;
	private Date thoigianbatdau;
	private Date thoigianketthuc;
	private Emp emp;
	
	@Id
	@Column(name = "emp_khoahoc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aht_khoahoc_id_khoahoc")
	public KhoaHoc getKhoaHoc() {
		return khoaHoc;
	}
	public void setKhoaHoc(KhoaHoc khoaHoc) {
		this.khoaHoc = khoaHoc;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getThoigianbatdau() {
		return thoigianbatdau;
	}
	public void setThoigianbatdau(Date thoigianbatdau) {
		this.thoigianbatdau = thoigianbatdau;
	}
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getThoigianketthuc() {
		return thoigianketthuc;
	}
	public void setThoigianketthuc(Date thoigianketthuc) {
		this.thoigianketthuc = thoigianketthuc;
	}
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aht_emp_id_emp")
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public EmpKhoaHoc() {
		
	}
	public EmpKhoaHoc(int id, String mota) {
		super();
		this.id = id;
		this.mota = mota;
	}
	public EmpKhoaHoc(int id, String mota, KhoaHoc khoaHoc) {
		super();
		this.id = id;
		this.mota = mota;
		this.khoaHoc = khoaHoc;
	}
	
	
	public EmpKhoaHoc(String mota, KhoaHoc khoaHoc) {
		super();
		this.mota = mota;
		this.khoaHoc = khoaHoc;
	}
	@Override
	public String toString() {
		return "EmpKhoaHoc [id=" + id + ", mota=" + mota;
	}
	 
	
}
