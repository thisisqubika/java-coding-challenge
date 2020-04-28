package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Model extends BaseModel{

	@ManyToOne
    @JoinColumn (name = "brand")
	private @NonNull Brand brand;

}
