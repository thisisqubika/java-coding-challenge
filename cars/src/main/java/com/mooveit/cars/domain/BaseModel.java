package com.mooveit.cars.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idModel;

	private @NonNull String name;

	private String fromYear;

	private String toYear;
	
	private boolean active;

	@ManyToOne
    @JoinColumn (name = "wheel")
	private @NonNull Wheel wheel;

	@ManyToOne
    @JoinColumn (name = "engine")
	private @NonNull Engine engine;

}
