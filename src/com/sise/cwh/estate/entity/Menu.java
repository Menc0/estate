package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * AbstractMenu entity provides the base persistence definition of the Menu
 * entity. @author MyEclipse Persistence Tools
 */
//@MappedSuperclass
@Entity
@Table(name = "menu", catalog = "estate")
public  class Menu implements java.io.Serializable {

	// Fields

	private String menuCode;
	private String sprrMenuCode;
	private String menuNm;
	private String url;
	private String trgt;
	private String seq;
	private String state;
	private String iconcls;

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(String sprrMenuCode, String menuNm, String url,
			String trgt, String seq, String state, String iconcls) {
		this.sprrMenuCode = sprrMenuCode;
		this.menuNm = menuNm;
		this.url = url;
		this.trgt = trgt;
		this.seq = seq;
		this.state = state;
		this.iconcls = iconcls;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "MENU_CODE", unique = true, nullable = false, length = 20)
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "SPRR_MENU_CODE", length = 20)
	public String getSprrMenuCode() {
		return this.sprrMenuCode;
	}

	public void setSprrMenuCode(String sprrMenuCode) {
		this.sprrMenuCode = sprrMenuCode;
	}

	@Column(name = "MENU_NM", length = 200)
	public String getMenuNm() {
		return this.menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	@Column(name = "URL", length = 1000)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "TRGT", length = 100)
	public String getTrgt() {
		return this.trgt;
	}

	public void setTrgt(String trgt) {
		this.trgt = trgt;
	}

	@Column(name = "SEQ", length = 10)
	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Column(name = "STATE", length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ICONCLS", length = 50)
	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	
	
}