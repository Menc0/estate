package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cuslikehos entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cuslikehos", catalog = "estate")
public class Cuslikehos implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hosNo;
	private String ip;

	// Constructors

	/** default constructor */
	public Cuslikehos() {
	}

	/** full constructor */
	public Cuslikehos(String hosNo, String ip) {
		this.hosNo = hosNo;
		this.ip = ip;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "HOS_NO", nullable = false)
	public String getHosNo() {
		return this.hosNo;
	}

	public void setHosNo(String hosNo) {
		this.hosNo = hosNo;
	}

	@Column(name = "IP", nullable = false)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}