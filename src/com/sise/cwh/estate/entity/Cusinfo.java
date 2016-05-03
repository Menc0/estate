package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * AbstractCusinfo entity provides the base persistence definition of the
 * Cusinfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
//@MappedSuperclass
@Entity
@Table(name = "cusinfo", catalog = "estate")
public class Cusinfo implements java.io.Serializable {

	// Fields

	private Integer cusNo;
	private String cusNm;
	private String cusIcd;
	private String cusTel;
	private String cusEmail;

	// Constructors

	/** default constructor */
	public Cusinfo() {
	}

	/** full constructor */
	public Cusinfo(String cusNm, String cusIcd, String cusTel,
			String cusEmail) {
		this.cusNm = cusNm;
		this.cusIcd = cusIcd;
		this.cusTel = cusTel;
		this.cusEmail = cusEmail;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "CUS_NO", unique = true, nullable = false)
	public Integer getCusNo() {
		return this.cusNo;
	}

	public void setCusNo(Integer cusNo) {
		this.cusNo = cusNo;
	}

	@Column(name = "CUS_NM", length = 20)
	public String getCusNm() {
		return this.cusNm;
	}

	public void setCusNm(String cusNm) {
		this.cusNm = cusNm;
	}

	@Column(name = "CUS_ICD", length = 20)
	public String getCusIcd() {
		return this.cusIcd;
	}

	public void setCusIcd(String cusIcd) {
		this.cusIcd = cusIcd;
	}

	@Column(name = "CUS_TEL", length = 20)
	public String getCusTel() {
		return this.cusTel;
	}

	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}

	@Column(name = "CUS_EMAIL" ,length = 20)
	public String getCusEmail() {
		return this.cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

}