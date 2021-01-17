package com.mooveit.cars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The entity to model the car's engines for each {@link AbstractSpec}.
 * <br>
 * Engines with the same type and power will be unique.
*/
@Entity
@Table(name = "engine")
public class Engine extends BaseEntity  {
	
	/**
	 * Engine's power
	 */
	@NotNull
	private Integer power;

	/**
	 * Engine's type
	 */
	@Enumerated(EnumType.STRING)
    @Column(length = 8)
	@NotNull
	private EngineType type;

	protected Engine() {
		super();
	}

	public Engine(Integer power, EngineType type) {
		super();
		this.power = power;
		this.type = type;
	}

	public Integer getPower() {
		return power;
	}

	public EngineType getType() {
		return type;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public void setType(EngineType type) {
		this.type = type;
	}
	
}
