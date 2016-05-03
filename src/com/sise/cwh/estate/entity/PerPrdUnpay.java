package com.sise.cwh.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * PerPrdUnpay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="per_prd_unpay"
    ,catalog="estate"
)

public class PerPrdUnpay  implements java.io.Serializable {


    // Fields    

     private Integer selNo;
     private Integer id;


    // Constructors

    /** default constructor */
    public PerPrdUnpay() {
    }

	/** minimal constructor */
    public PerPrdUnpay(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public PerPrdUnpay(Integer selNo, Integer id) {
        this.selNo = selNo;
        this.id = id;
    }

   
    // Property accessors
    @Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    @Column(name="SEL_NO", nullable=false)
    public Integer getSelNo() {
        return this.selNo;
    }
    
    public void setSelNo(Integer selNo) {
        this.selNo = selNo;
    }
    








}