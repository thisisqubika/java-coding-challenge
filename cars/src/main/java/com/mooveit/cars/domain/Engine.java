package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class Engine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEngine;

	private @NonNull Integer power;

	private @NonNull String type;

	public Engine(@NonNull Integer power, @NonNull String type) {
		super();
		this.power = power;
		this.type = type;
	}
	
}
