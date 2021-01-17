package com.mooveit.cars.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Register every ingestion made for a {@link Brand} from a specific `source`.
 * <br> 
 * Sources could be URIs, Paths, etc.
 */
@Entity
@Table(name = "ingestion")
public class Ingestion extends BaseEntity {

	@NotNull
	@ManyToOne
	private Brand brand;

	@NotNull
	private Date date;

	@NotNull
	private String source;

	private Long totalSpecs;

	private Long newSpecs;

	public Ingestion() {
		super();
	}

	public Ingestion(Brand brand, Date date, String source, Long totalSpecs, Long newSpecs) {
		super();
		this.brand = brand;
		this.date = date;
		this.source = source;
		this.totalSpecs = totalSpecs;
		this.newSpecs = newSpecs;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getTotalSpecs() {
		return totalSpecs;
	}

	public void setTotalSpecs(Long totalSpecs) {
		this.totalSpecs = totalSpecs;
	}

	public Long getNewSpecs() {
		return newSpecs;
	}

	public void setNewSpecs(Long newSpecs) {
		this.newSpecs = newSpecs;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	
}
