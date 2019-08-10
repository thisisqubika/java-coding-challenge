package com.mooveit.cars.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity to model specific variants for a car {@link Specification}, as for example
 * different {@link Engine} or {@link Wheel}. 
 */
@Entity
@DiscriminatorValue("modification")
public class Modification extends AbstractSpec {

	/**
	 * Specification car line
	 */
	@NotNull
	private String line;

	protected Modification() {
		super();
	}

	public Modification(@NotNull String name, @NotNull Integer from, Integer to, String line, Engine engine,
			Wheel wheel) {
		super(name, from, to, engine, wheel);
		this.line = line;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

}
