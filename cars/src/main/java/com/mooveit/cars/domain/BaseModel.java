package com.mooveit.cars.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.NonNull;

@MappedSuperclass
public class BaseModel {

	@Id
	@GeneratedValue
	private Integer idModel;

	private @NonNull String name;

	private String line;

	private String fromYear;

	private String toYear;

	@ManyToOne
    @JoinColumn (name = "wheel")
	private @NonNull Wheel wheel;

	@ManyToOne
    @JoinColumn (name = "engine")
	private @NonNull Engine engine;

}
