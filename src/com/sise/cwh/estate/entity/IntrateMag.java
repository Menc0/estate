package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IntrateMag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "intrate_mag", catalog = "estate")
public class IntrateMag implements java.io.Serializable {

	// Fields

	private Integer intrateId;
	private Integer bystgSum;
	private Double intrate;

	// Constructors

	/** default constructor */
	public IntrateMag() {
	}

	/** full constructor */
	public IntrateMag(Integer bystgSum, Double intrate) {
		this.bystgSum = bystgSum;
		this.intrate = intrate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "INTRATE_ID", unique = true, nullable = false)
	public Integer getIntrateId() {
		return this.intrateId;
	}

	public void setIntrateId(Integer intrateId) {
		this.intrateId = intrateId;
	}

	@Column(name = "BYSTG_SUM", nullable = false)
	public Integer getBystgSum() {
		return this.bystgSum;
	}

	public void setBystgSum(Integer bystgSum) {
		this.bystgSum = bystgSum;
	}

	@Column(name = "INTRATE", nullable = false, precision = 22, scale = 0)
	public Double getIntrate() {
		return this.intrate;
	}

	public void setIntrate(Double intrate) {
		this.intrate = intrate;
	}

}