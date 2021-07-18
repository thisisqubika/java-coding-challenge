package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WHEELS")
public class WHEELS  implements Serializable{

	private static final long serialVersionUID = 5886434486065777739L;
		
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WHEEL_ID")
	private int wheelid;
	
	@OneToOne
    //@JoinColumn(name = "MODEL_ID", insertable = false, updatable = false, nullable = false)
	@JoinColumn(name = "MODEL_ID",nullable = false)
	private  MODEL primarymodel;
	
	@Column(name = "SIZE")
	private String size;
	
	public MODEL getPrimarymodel() {
		return primarymodel;
	}

	public void setPrimarymodel(MODEL primarymodel) {
		this.primarymodel = primarymodel;
	}

	@Column(name = "TYPE")
	private String type;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWheelid() {
		return wheelid;
	}

	public void setWheelid(int wheelid) {
		this.wheelid = wheelid;
	}
	
}
