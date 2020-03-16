package com.th.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "aht_baikiemtra")
public class BaiKiemTra {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idbaikt;
	private String tenbaikiemtra;
	private String motakt;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date thoigiankiemtra;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aht_bkt_kh")
	private KhoaHoc kh;
	
	@OneToMany(mappedBy = "baikt", cascade = CascadeType.REMOVE) 
	private List<DiemKiemTra> diemKiemTra;
	
	public int getIdbaikt() {
		return idbaikt;
	}
	public void setIdbaikt(int idbaikt) {
		this.idbaikt = idbaikt;
	}
	public String getTenbaikiemtra() {
		return tenbaikiemtra;
	}
	public void setTenbaikiemtra(String tenbaikiemtra) {
		this.tenbaikiemtra = tenbaikiemtra;
	}
	public String getMotakt() {
		return motakt;
	}
	public void setMotakt(String motakt) {
		this.motakt = motakt;
	}
	 
	public KhoaHoc getKh() {
		return kh;
	}
	public void setKh(KhoaHoc kh) {
		this.kh = kh;
	}
	 
	public List<DiemKiemTra> getDiemKiemTra() {
		return diemKiemTra;
	}
	public void setDiemKiemTra(List<DiemKiemTra> diemKiemTra) {
		this.diemKiemTra = diemKiemTra;
	}
	
	
	
	public Date getThoigiankiemtra() {
		return thoigiankiemtra;
	}
	public void setThoigiankiemtra(Date thoigiankiemtra) {
		this.thoigiankiemtra = thoigiankiemtra;
	}
	
	public BaiKiemTra(){
		
	}
	public BaiKiemTra(int idbaikt, String tenbaikiemtra, String motakt, KhoaHoc kh) {
		super();
		this.idbaikt = idbaikt;
		this.tenbaikiemtra = tenbaikiemtra;
		this.motakt = motakt;
		this.kh = kh;
	}
	
	
}
