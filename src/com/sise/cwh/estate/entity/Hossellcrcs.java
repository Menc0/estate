package com.sise.cwh.estate.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * AbstractHossellcrcs entity provides the base persistence definition of the
 * Hossellcrcs entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
//@MappedSuperclass
@Entity
@Table(name = "hossellcrcs", catalog = "estate")
/*public class Hossellcrcs implements java.io.Serializable {

	// Fields

	private Integer selNo;
	private String bldNo;
	private Integer hosNo;
	private Integer wrkrNo;
	private Integer cusNo;
	private double hosPft;
	private Date txnDt;
	private String comment;

	// Constructors

	*//** default constructor *//*
	public Hossellcrcs() {
	}

	*//** full constructor *//*
	public Hossellcrcs(String bldNo, Integer hosNo, Integer wrkrNo,
			Integer cusNo, double hosPft, Date txnDt, String comment) {
		this.bldNo = bldNo;
		this.hosNo = hosNo;
		this.wrkrNo = wrkrNo;
		this.cusNo = cusNo;
		this.hosPft = hosPft;
		this.txnDt = txnDt;
		this.comment = comment;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "SEL_NO", unique = true, nullable = false)
	public Integer getSelNo() {
		return this.selNo;
	}

	public void setSelNo(Integer selNo) {
		this.selNo = selNo;
	}

	@Column(name = "BLD_NO", length = 20)
	public String getBldNo() {
		return this.bldNo;
	}

	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}

	@Column(name = "HOS_NO")
	public Integer getHosNo() {
		return this.hosNo;
	}

	public void setHosNo(Integer hosNo) {
		this.hosNo = hosNo;
	}

	@Column(name = "WRKR_NO")
	public Integer getWrkrNo() {
		return this.wrkrNo;
	}

	public void setWrkrNo(Integer wrkrNo) {
		this.wrkrNo = wrkrNo;
	}

	@Column(name = "CUS_NO")
	public Integer getCusNo() {
		return this.cusNo;
	}

	public void setCusNo(Integer cusNo) {
		this.cusNo = cusNo;
	}

	@Column(name = "HOS_PFT", precision = 22, scale = 0)
	public double getHosPft() {
		return this.hosPft;
	}

	public void setHosPft(double hosPft) {
		this.hosPft = hosPft;
	}

	@Column(name = "TXN_DT", length = 19)
	public Date getTxnDt() {
		return this.txnDt;
	}

	public void setTxnDt(Date txnDt) {
		this.txnDt = txnDt;
	}

	@Column(name = "COMMENT", length = 100)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}*/

public class Hossellcrcs implements java.io.Serializable {

	// Fields

	private Integer selNo;
	private Wrkrinfo wrkrinfo;
	private Cusinfo cusinfo;
	private Housemag housemag;
	private Double hosPft;
	//private Timestamp txnDt;
	private String comment;
	private int prdSumId;
	private Double fsdpdPay;

	// Constructors

	/** default constructor */
	public Hossellcrcs() {
	}

	/** minimal constructor */
	public Hossellcrcs(Integer selNo) {
		this.selNo = selNo;
	}

	/** full constructor */
	public Hossellcrcs(Integer selNo, Wrkrinfo wrkrinfo, Cusinfo cusinfo,
			Housemag housemag, Double hosPft, String comment, int prdSumId, Double fsdpdPay) {
		this.selNo = selNo;
		this.wrkrinfo = wrkrinfo;
		this.cusinfo = cusinfo;
		this.housemag = housemag;
		this.hosPft = hosPft;
		//this.txnDt = txnDt;
		this.comment = comment;
		this.prdSumId = prdSumId;
		this.fsdpdPay = fsdpdPay;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "SEL_NO", unique = true, nullable = false)
	public Integer getSelNo() {
		return this.selNo;
	}

	public void setSelNo(Integer selNo) {
		this.selNo = selNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WRKR_NO")
	public Wrkrinfo getWrkrinfo() {
		return this.wrkrinfo;
	}

	public void setWrkrinfo(Wrkrinfo wrkrinfo) {
		this.wrkrinfo = wrkrinfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUS_NO")
	public Cusinfo getCusinfo() {
		return this.cusinfo;
	}

	public void setCusinfo(Cusinfo cusinfo) {
		this.cusinfo = cusinfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "HOS_NO", referencedColumnName = "HOS_NO"),
			@JoinColumn(name = "BLD_NO", referencedColumnName = "BLD_NO") })
	public Housemag getHousemag() {
		return this.housemag;
	}

	public void setHousemag(Housemag housemag) {
		this.housemag = housemag;
	}

	@Column(name = "HOS_PFT", precision = 22, scale = 0)
	public Double getHosPft() {
		return this.hosPft;
	}

	public void setHosPft(Double hosPft) {
		this.hosPft = hosPft;
	}

	/*@Column(name = "TXN_DT", length = 19)
	public Timestamp getTxnDt() {
		return this.txnDt;
	}

	public void setTxnDt(Timestamp txnDt) {
		this.txnDt = txnDt;
	}*/

	@Column(name = "COMMENT", length = 100)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "PRD_SUM_ID", length = 11)
	public int getPrdSumId() {
		return prdSumId;
	}

	public void setPrdSumId(int prdSumId) {
		this.prdSumId = prdSumId;
	}

	@Column(name = "FSDPD_PAY")
	public Double getFsdpdPay() {
		return fsdpdPay;
	}

	public void setFsdpdPay(Double fsdpdPay) {
		this.fsdpdPay = fsdpdPay;
	}

	
	
}