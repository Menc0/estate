package com.sise.cwh.estate.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * HousemagId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class HousemagId implements java.io.Serializable {

	// Fields

	private String hosNo;
	private String bldNo;

	// Constructors

	/** default constructor */
	public HousemagId() {
	}

	/** full constructor */
	public HousemagId(String hosNo, String bldNo) {
		this.hosNo = hosNo;
		this.bldNo = bldNo;
	}

	// Property accessors

	@Column(name = "HOS_NO", nullable = false, length = 11)
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
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HousemagId))
			return false;
		HousemagId castOther = (HousemagId) other;

		return ((this.getHosNo() == castOther.getHosNo()) || (this.getHosNo() != null
				&& castOther.getHosNo() != null && this.getHosNo().equals(
				castOther.getHosNo())))
				&& ((this.getBldNo() == castOther.getBldNo()) || (this
						.getBldNo() != null && castOther.getBldNo() != null && this
						.getBldNo().equals(castOther.getBldNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHosNo() == null ? 0 : this.getHosNo().hashCode());
		result = 37 * result
				+ (getBldNo() == null ? 0 : this.getBldNo().hashCode());
		return result;
	}

}