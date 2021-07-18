package com.mooveit.cars.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SUBMODEL")
public class SUBMODEL  implements Serializable {
	
	private static final long serialVersionUID = 5186434486065777539L;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUB_MODEL_ID")
	private int subModelid;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID",nullable = false)
	private  MODEL primarymodel;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="parentsubmodel")
	private  List<MODEL> childmodellst;
	
	
	public MODEL getPrimarymodel() {
		return primarymodel;
	}

	public void setPrimarymodel(MODEL primarymodel) {
		this.primarymodel = primarymodel;
	}

	public List<MODEL> getChildmodellst() {
		return childmodellst;
	}

	public void setChildmodellst(List<MODEL> childmodellst) {
		this.childmodellst = childmodellst;
	}

	

	
}