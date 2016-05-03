package com.sise.cwh.estate.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * AbstractRolemenu entity provides the base persistence definition of the
 * Rolemenu entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
//@MappedSuperclass
@Entity
@Table(name = "rolemenu", catalog = "estate")
public  class Rolemenu implements java.io.Serializable {

	// Fields

	private RolemenuId id;

	// Constructors

	/** default constructor */
	public Rolemenu() {
	}

	/** full constructor */
	public Rolemenu(RolemenuId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, length= 10)),
			@AttributeOverride(name = "menuCode", column = @Column(name = "MENU_CODE", nullable = false, length = 20)) })
	public RolemenuId getId() {
		return this.id;
	}

	public void setId(RolemenuId id) {
		this.id = id;
	}

}