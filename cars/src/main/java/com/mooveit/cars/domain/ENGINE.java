package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENGINE")
public class ENGINE  implements Serializable {

	private static final long serialVersionUID = 5886234486065777539L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENGINE_ID")
	private int engineid;
	
	@OneToOne
    @JoinColumn(name = "MODEL_ID",nullable = false)
	private  MODEL primarymodel;
	
	public MODEL getPrimarymodel() {
		return primarymodel;
	}

	public void setPrimarymodel(MODEL primarymodel) {
		this.primarymodel = primarymodel;
	}

	@Column(name = "POWER")
	private String power;
	
	@Column(name = "TYPE")
	private String type;

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEngineid() {
		return engineid;
	}

	public void setEngineid(int engineid) {
		this.engineid = engineid;
	}
	
}
