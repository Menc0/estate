package com.sise.cwh.estate.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PerPrdPayed entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "per_prd_payed", catalog = "estate")
public class PerPrdPayed implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer selNo;
	private Timestamp payDt;

	// Constructors

	/** default constructor */
	public PerPrdPayed() {
	}

	/** minimal constructor */
	public PerPrdPayed(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public PerPrdPayed(Integer id, Integer selNo, Timestamp payDt) {
		this.selNo = selNo;
		this.payDt = payDt;
		this.id = id;
	}

	// Property accessors
	
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SEL_NO", length = 11,nullable = false)
	public Integer getSelNo() {
		return this.selNo;
	}

	
	public void setSelNo(Integer selNo) {
		this.selNo = selNo;
	}

	/*@Column(name = "PAY_DT", length = 19)
	public Timestamp getPayDt() {
		return this.payDt;
	}

	public void setPayDt(Timestamp payDt) {
		this.payDt = payDt;
	}*/

}