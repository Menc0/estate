package com.sise.cwh.estate.entity;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AbstractHousemag entity provides the base persistence definition of the
 * Housemag entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
//@MappedSuperclass
@Entity
@Table(name = "housemag", catalog = "estate")
public  class Housemag implements java.io.Serializable {

	// Fields

	/*private String hosNo;
	private String bldNo;*/
	private HousemagId id;
	private double hosArea;
	private String hosTp;
	private double costPce;
	private double sellPce;
	private double dscntRate;
	private Integer sellSt;
	private String hosIntro;
	private String hosImg;
	private Integer isRecmd;
	//private Timestamp createDate;

	// Constructors

	/** default constructor */
	public Housemag() {
	}

	/** minimal constructor */
	public Housemag(HousemagId id) {
		this.id = id;
	}

	/** full constructor */
	public Housemag(HousemagId id, double hosArea, String hosTp,
			double costPce, double sellPce, double dscntRate, Integer sellSt,
			String hosIntro, String hosImg, Integer isRecmd) {
		
		this.id = id;
		this.hosArea = hosArea;
		this.hosTp = hosTp;
		this.costPce = costPce;
		this.sellPce = sellPce;
		this.dscntRate = dscntRate;
		this.sellSt = sellSt;
		this.hosIntro = hosIntro;
		this.hosImg = hosImg;
		this.isRecmd = isRecmd;
	}

	/*// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "HOS_NO", unique = true, nullable = false, length = 11)
	public String getHosNo() {
		return this.hosNo;
	}

	public void setHosNo(String hosNo) {
		this.hosNo = hosNo;
	}
	
	@Column(name = "BLD_NO", nullable = false, length = 20)
	public String getBldNo() {
		return this.bldNo;
	}

	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}*/
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hosNo", column = @Column(name = "HOS_NO", nullable = false, length = 11)),
			@AttributeOverride(name = "bldNo", column = @Column(name = "BLD_NO", nullable = false, length = 20)) })
	public HousemagId getId() {
		return this.id;
	}

	public void setId(HousemagId id) {
		this.id = id;
	}

	@Column(name = "HOS_AREA", precision = 22, scale = 0)
	public double getHosArea() {
		return this.hosArea;
	}

	public void setHosArea(double hosArea) {
		this.hosArea = hosArea;
	}

	@Column(name = "HOS_TP")
	public String getHosTp() {
		return this.hosTp;
	}

	public void setHosTp(String hosTp) {
		this.hosTp = hosTp;
	}

	@Column(name = "COST_PCE", precision = 22, scale = 0)
	public double getCostPce() {
		return this.costPce;
	}

	public void setCostPce(double costPce) {
		this.costPce = costPce;
	}

	@Column(name = "SELL_PCE", precision = 22, scale = 0)
	public double getSellPce() {
		return this.sellPce;
	}

	public void setSellPce(double sellPce) {
		this.sellPce = sellPce;
	}

	@Column(name = "DSCNT_RATE", precision = 22, scale = 0)
	public double getDscntRate() {
		return this.dscntRate;
	}

	public void setDscntRate(double dscntRate) {
		this.dscntRate = dscntRate;
	}

	@Column(name = "SELL_ST")
	public Integer getSellSt() {
		return this.sellSt;
	}

	public void setSellSt(Integer sellSt) {
		this.sellSt = sellSt;
	}

	@Column(name = "HOS_INTRO")
	public String getHosIntro() {
		return this.hosIntro;
	}

	public void setHosIntro(String hosIntro) {
		this.hosIntro = hosIntro;
	}

	@Column(name = "HOS_IMG", length = 1000)
	public String getHosImg() {
		return this.hosImg;
	}

	public void setHosImg(String hosImg) {
		this.hosImg = hosImg;
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