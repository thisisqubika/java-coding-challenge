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
public class Wheel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idWheel;

	private @NonNull String size;

	private @NonNull String type;

	public Wheel(@NonNull String size, @NonNull String type) {
		super();
		this.size = size;
		this.type = type;
	}
	
}
