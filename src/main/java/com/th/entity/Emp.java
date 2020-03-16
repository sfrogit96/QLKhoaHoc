package com.th.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
 

@Entity
@Table(name = "aht_emp")
public class Emp {

	private int id;
	@NotEmpty(message = "Tên học viên không được để trống!")
	private String ten;
	@NotNull(message = "Ngày sinh không được để trống!")
	private Date ngaysinh;
	private String gioitinh;
	private String diachi;
	@NotEmpty(message = "Email không được để trống!")
	@Email(message = "Email sai cú pháp!")
	private String email;
	@Pattern(regexp = "(\\+84|0)[0-9]{9}", message = "Không đúng cú pháp số điện thoại! +84/0 + 9 numbers")
	@NotEmpty(message = "Số điện thoại không được để trống!")
	private String sdt;
	private ChucVu chucvu;
	
//	private Set<EmpKhoaHoc> empKhoaHoc = new HashSet<EmpKhoaHoc>();
	private List<EmpKhoaHoc> empKhoaHoc;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "emp_ten")
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	@Column(name = "emp_ngaysinh")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	
	@Column(name = "emp_gioitinh")
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	
	@Column(name = "emp_diachi")
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	@Column(name = "emp_email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "emp_sdt")
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
  	
	public Emp() {
		
	}
	public Emp(String ten, Date ngaysinh, String gioitinh, String diachi, String email, String sdt) {
		super();
		this.ten = ten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.email = email;
		this.sdt = sdt;
	}
	
	
	
	public Emp(int id, String ten, Date ngaysinh, String gioitinh, String diachi, String email, String sdt,
			ChucVu chucvu) {
		this.id = id;
		this.ten = ten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.email = email;
		this.sdt = sdt;
		this.chucvu = chucvu;
	}
	@OneToMany(mappedBy = "emp", cascade = CascadeType.REMOVE) 
	public List<EmpKhoaHoc> getEmpKhoaHoc() {
		return empKhoaHoc;
	}
	public void setEmpKhoaHoc(List<EmpKhoaHoc> empKhoaHoc) {
		this.empKhoaHoc = empKhoaHoc;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aht_chucvu_idchucvu")
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "aht_chucvu_idchucvu")
	public ChucVu getChucvu() {
		return chucvu;
	}
	public void setChucvu(ChucVu chucvu) {
		this.chucvu = chucvu;
	}
	
	@Override
	public String toString() {
		return "Emp [id=" + id + ", ten=" + ten + ", ngaysinh=" + ngaysinh + ", gioitinh=" + gioitinh + ", diachi="
				+ diachi + ", email=" + email + ", sdt=" + sdt + "]";
	}
	
	
	
}
