package com.mooveit.cars.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Base specification info for both car's {@link Specification} and {@link Modification}.
 * <br>
 * The complete hierarchy is mapped into a single table to make data access more
 * efficient, avoiding the <I>inner join</i> between tables.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="spec_type")
@Table(name="specifications")
public abstract class AbstractSpec extends BaseEntity {
	
	/**
	 * Car's model name
	 */
	@NotNull
	@Column(length = 125)
	private String name;

	/**
	 * Year of putting into production
	 */
	@Column(name = "yearFrom")
	private Integer from;

	/**
	 * Year of stopping production
	 */
	@Column(name = "yearTo")
	private Integer to;
		
	@ManyToOne(cascade = CascadeType.ALL)
	private Engine engine;

	@ManyToOne(cascade = CascadeType.ALL)
	private Wheel wheel;

	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	
	protected AbstractSpec() {
	}

	public AbstractSpec(String name, Integer from, Integer to, Engine engine, Wheel wheel) {
		this.name = name;
		this.from = from;
		this.to = to;
		this.engine = engine;
		this.wheel = wheel;
	}
	

	public String getName() {
		return name;
	}

	public Integer getFrom() {
		return from;
	}

	public Integer getTo() {
		return to;
	}

	public Engine getEngine() {
		return engine;
	}

	public Wheel getWheel() {
		return wheel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
	
}
