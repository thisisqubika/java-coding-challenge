package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Engine {
	
	@Id
	@GeneratedValue
	private Integer idEngine;

	private @NonNull Integer power;

	private @NonNull String type;
	

}
