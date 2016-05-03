package com.sise.cwh.estate.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * AbstractBuildmag entity provides the base persistence definition of the
 * Buildmag entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
//@MappedSuperclass
@Entity
@Table(name = "buildmag", catalog = "estate")
public class Buildmag implements java.io.Serializable {

	// Fields

	private String bldNo;
	private String bldNm;
	private String bldSite;
	private double bldArea;
	private Integer floSum;
	private Integer houseSum;
	private double bldInvestMon;
	private String bldIntro;
	private String bldImg;
	private Integer isRecmd;

	// Constructors

	/** default constructor */
	public Buildmag() {
	}

	/** full constructor */
	public Buildmag(String bldNm, String bldSite, double bldArea,
			Integer floSum, Integer houseSum, double bldInvestMon,
			String bldIntro, String bldImg, Integer isRecmd) {
		this.bldNm = bldNm;
		this.bldSite = bldSite;
		this.bldArea = bldArea;
		this.floSum = floSum;
		this.houseSum = houseSum;
		this.bldInvestMon = bldInvestMon;
		this.bldIntro = bldIntro;
		this.bldImg = bldImg;
		this.isRecmd = isRecmd;
	}

	// Property accessors
	@Id
	//@GeneratedValue
	@Column(name = "BLD_NO", unique = true, nullable = false, length = 20)
	public String getBldNo() {
		return this.bldNo;
	}

	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}

	@Column(name = "BLD_NM", length = 20)
	public String getBldNm() {
		return this.bldNm;
	}

	public void setBldNm(String bldNm) {
		this.bldNm = bldNm;
	}

	@Column(name = "BLD_SITE", length = 100)
	public String getBldSite() {
		return this.bldSite;
	}

	public void setBldSite(String bldSite) {
		this.bldSite = bldSite;
	}

	@Column(name = "BLD_AREA", precision = 22, scale = 0)
	public double getBldArea() {
		return this.bldArea;
	}

	public void setBldArea(double bldArea) {
		this.bldArea = bldArea;
	}

	@Column(name = "FLO_SUM")
	public Integer getFloSum() {
		return this.floSum;
	}

	public void setFloSum(Integer floSum) {
		this.floSum = floSum;
	}

	@Column(name = "HOUSE_SUM")
	public Integer getHouseSum() {
		return this.houseSum;
	}

	public void setHouseSum(Integer houseSum) {
		this.houseSum = houseSum;
	}

	@Column(name = "BLD_INVEST_MON", precision = 22, scale = 0)
	public double getBldInvestMon() {
		return this.bldInvestMon;
	}

	public void setBldInvestMon(double bldInvestMon) {
		this.bldInvestMon = bldInvestMon;
	}

	@Column(name = "BLD_INTRO", length = 10000)
	public String getBldIntro() {
		return this.bldIntro;
	}

	public void setBldIntro(String bldIntro) {
		this.bldIntro = bldIntro;
	}

	@Column(name = "BLD_IMG", length = 100)
	public String getBldImg() {
		return this.bldImg;
	}

	public void setBldImg(String bldImg) {
		this.bldImg = bldImg;
	}
	
	@Column(name = "IS_RECMD")
	public Integer getIsRecmd() {
		return this.isRecmd;
	}

	public void setIsRecmd(Integer isRecmd) {
		this.isRecmd = isRecmd;
	}
	/*@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}*/

}