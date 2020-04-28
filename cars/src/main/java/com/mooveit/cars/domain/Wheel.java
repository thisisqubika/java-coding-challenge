package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Wheel {
	
	@Id
	@GeneratedValue
	private Integer idWheel;

	private @NonNull String size;

	private @NonNull String type;

}
