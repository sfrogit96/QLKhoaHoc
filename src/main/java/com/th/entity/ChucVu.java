package com.th.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aht_chucvu")
public class ChucVu {
	
	private int id;
	private String tenchucvu;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idchucvu")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenchucvu() {
		return tenchucvu;
	}
	public void setTenchucvu(String tenchucvu) {
		this.tenchucvu = tenchucvu;
	}
	public ChucVu() {
		
	}
	public ChucVu(int id, String tenchucvu) {
		super();
		this.id = id;
		this.tenchucvu = tenchucvu;
	}
	
}
