package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BystgPayCrcs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bystg_pay_crcs", catalog = "estate")
public class BystgPayCrcs implements java.io.Serializable {

	// Fields

	private Integer sellNo;
	private Integer payedSum;
	private Integer unpaySum;
	private Double perPrdMon;
	private Double fsdpdPay;

	// Constructors

	/** default constructor */
	public BystgPayCrcs() {
	}

	/** minimal constructor */
	public BystgPayCrcs(Integer sellNo) {
		this.sellNo = sellNo;
	}

	/** full constructor */
	public BystgPayCrcs(Integer sellNo, Integer payedSum, Integer unpaySum,
			Double perPrdMon, Double fsdpdPay) {
		this.sellNo = sellNo;
		this.payedSum = payedSum;
		this.unpaySum = unpaySum;
		this.perPrdMon = perPrdMon;
		this.fsdpdPay = fsdpdPay;
	}

	// Property accessors
	@Id
	@Column(name = "SELL_NO", unique = true, nullable = false)
	public Integer getSellNo() {
		return this.sellNo;
	}

	public void setSellNo(Integer sellNo) {
		this.sellNo = sellNo;
	}

	@Column(name = "PAYED_SUM")
	public Integer getPayedSum() {
		return this.payedSum;
	}

	public void setPayedSum(Integer payedSum) {
		this.payedSum = payedSum;
	}

	@Column(name = "UNPAY_SUM")
	public Integer getUnpaySum() {
		return this.unpaySum;
	}

	public void setUnpaySum(Integer unpaySum) {
		this.unpaySum = unpaySum;
	}

	@Column(name = "PER_PRD_MON", precision = 22, scale = 0)
	public Double getPerPrdMon() {
		return this.perPrdMon;
	}

	public void setPerPrdMon(Double perPrdMon) {
		this.perPrdMon = perPrdMon;
	}

	@Column(name = "FSDPD_PAY", precision = 22, scale = 0)
	public Double getFsdpdPay() {
		return fsdpdPay;
	}

	public void setFsdpdPay(Double fsdpdPay) {
		this.fsdpdPay = fsdpdPay;
	}
	
	

}