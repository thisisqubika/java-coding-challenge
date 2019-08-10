package com.mooveit.cars.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Car's brand model. Root entity that model the complete and updated catalog
 * for a specific car Brand.
 */
@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {

	@NotNull
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
	private List<Specification> specifications;

	protected Brand() {
		super();
	};

	public Brand(String name) {
		super();
		this.name = name;
		this.specifications = new ArrayList<Specification>();
	}

	public void addSpecification(Specification spec) {
		this.specifications.add(spec);
	}

	public Stream<Specification> getSpecifications() {
		return specifications.stream();
	}

	protected void setSpecifications(List<Specification> specifications) {
		this.specifications = specifications;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
