package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RolemenuId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RolemenuId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String menuCode;

	// Constructors

	/** default constructor */
	public RolemenuId() {
	}

	/** full constructor */
	public RolemenuId(String roleId, String menuCode) {
		this.roleId = roleId;
		this.menuCode = menuCode;
	}

	// Property accessors

	@Column(name = "ROLE_ID", nullable = false)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MENU_CODE", nullable = false, length = 20)
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolemenuId))
			return false;
		RolemenuId castOther = (RolemenuId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getMenuCode() == castOther.getMenuCode()) || (this
						.getMenuCode() != null
						&& castOther.getMenuCode() != null && this
						.getMenuCode().equals(castOther.getMenuCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getMenuCode() == null ? 0 : this.getMenuCode().hashCode());
		return result;
	}

}