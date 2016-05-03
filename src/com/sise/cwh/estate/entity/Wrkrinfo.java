package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * AbstractWrkrinfo entity provides the base persistence definition of the
 * Wrkrinfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
//@MappedSuperclass
@Entity
@Table(name = "wrkrinfo", catalog = "estate")
public  class Wrkrinfo implements java.io.Serializable {

	// Fields

	private String wrkrNo;
	private String wrkrNm;
	private String roleId;
	private String wrkrIcd;
	private String wrkrTell;
	private String loginPsw;
	private String wrkrPst;
	private double sellScr;

	// Constructors

	/** default constructor */
	public Wrkrinfo() {
	}

	/** full constructor */
	public Wrkrinfo(String wrkrNm, String roleId, String wrkrIcd,
			String wrkrTell, String loginPsw, String wrkrPst, double sellScr) {
		this.wrkrNm = wrkrNm;
		this.roleId = roleId;
		this.wrkrIcd = wrkrIcd;
		this.wrkrTell = wrkrTell;
		this.loginPsw = loginPsw;
		this.wrkrPst = wrkrPst;
		this.sellScr = sellScr;
	}

	// Property accessors
	@Id
	//@GeneratedValue
	@Column(name = "WRKR_NO", unique = true, nullable = false)
	public String getWrkrNo() {
		return this.wrkrNo;
	}

	public void setWrkrNo(String wrkrNo) {
		this.wrkrNo = wrkrNo;
	}

	@Column(name = "WRKR_NM", length = 20)
	public String getWrkrNm() {
		return this.wrkrNm;
	}

	public void setWrkrNm(String wrkrNm) {
		this.wrkrNm = wrkrNm;
	}

	@Column(name = "ROLE_ID" ,length = 10)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "WRKR_ICD", length = 20)
	public String getWrkrIcd() {
		return this.wrkrIcd;
	}

	public void setWrkrIcd(String wrkrIcd) {
		this.wrkrIcd = wrkrIcd;
	}

	@Column(name = "WRKR_TELL", length = 20)
	public String getWrkrTell() {
		return this.wrkrTell;
	}

	public void setWrkrTell(String wrkrTell) {
		this.wrkrTell = wrkrTell;
	}

	@Column(name = "LOGIN_PSW", length = 20)
	public String getLoginPsw() {
		return this.loginPsw;
	}

	public void setLoginPsw(String loginPsw) {
		this.loginPsw = loginPsw;
	}

	@Column(name = "WRKR_PST", length = 20)
	public String getWrkrPst() {
		return this.wrkrPst;
	}

	public void setWrkrPst(String wrkrPst) {
		this.wrkrPst = wrkrPst;
	}

	@Column(name = "SELL_SCR", precision = 22, scale = 0)
	public double getSellScr() {
		return this.sellScr;
	}

	public void setSellScr(double sellScr) {
		this.sellScr = sellScr;
	}

}