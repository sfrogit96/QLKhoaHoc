package com.th.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
 

@Entity
@Table(name = "aht_khoahoc")
public class KhoaHoc {
	
	private int khoahoc_id;
	
	@NotEmpty(message = "Tên khóa học không được để trống!")
	private String tenkhoahoc;
	
	@NotNull(message = "Ngày bắt đầu không được để trống!")
	private Date ngaybatdau;
	@NotNull(message = "Ngày kết thúc không được để trống!")
	private Date ngayketthuc;
	
	private boolean status;
	
	private List<EmpKhoaHoc> empKhoaHoc;
	
	private List<BaiKiemTra> baiKiemTra;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getKhoahoc_id() {
		return khoahoc_id;
	}
	public void setKhoahoc_id(int khoahoc_id) {
		this.khoahoc_id = khoahoc_id;
	}
	

	public String getTenkhoahoc() {
		return tenkhoahoc;
	}
	public void setTenkhoahoc(String tenkhoahoc) {
		this.tenkhoahoc = tenkhoahoc;
	}
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	
 
	public KhoaHoc() {
		
	}
	public KhoaHoc(String tenkhoahoc, Date ngaybatdau, Date ngayketthuc) {
		super();
		this.tenkhoahoc = tenkhoahoc;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
	}
	
	
	 public KhoaHoc(int khoahoc_id, String tenkhoahoc, Date ngaybatdau, Date ngayketthuc) {
		super();
		this.khoahoc_id = khoahoc_id;
		this.tenkhoahoc = tenkhoahoc;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
	}
	 
	@OneToMany(mappedBy = "khoaHoc")
	public List<EmpKhoaHoc> getEmpKhoaHoc() {
		return empKhoaHoc;
	}
	public void setEmpKhoaHoc(List<EmpKhoaHoc> empKhoaHoc) {
		this.empKhoaHoc = empKhoaHoc;
	}
	 
	@OneToMany(mappedBy = "kh")
	public List<BaiKiemTra> getBaiKiemTra() {
		return baiKiemTra;
	}
	public void setBaiKiemTra(List<BaiKiemTra> baiKiemTra) {
		this.baiKiemTra = baiKiemTra;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "KhoaHoc [khoahoc_id=" + khoahoc_id + ", tenkhoahoc=" + tenkhoahoc + ", ngaybatdau=" + ngaybatdau
				+ ", ngayketthuc=" + ngayketthuc + ", empKhoaHoc=" + empKhoaHoc + "]";
	} 
}
