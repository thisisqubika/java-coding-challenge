package com.mooveit.cars.model;

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
import com.mooveit.cars.domain.MODEL;

public class SUBMODELpojo  implements Serializable {
	
	private static final long serialVersionUID = 5186433386065777539L;

	private  List<MODELpojo> childmodellst;

	public List<MODELpojo> getChildmodellst() {
		return childmodellst;
	}

	public void setChildmodellst(List<MODELpojo> childmodellst) {
		this.childmodellst = childmodellst;
	}

	
	
	

	

	
}