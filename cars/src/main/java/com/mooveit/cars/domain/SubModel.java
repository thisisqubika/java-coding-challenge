package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
public class SubModel extends BaseModel {
	
	@ManyToOne
    @JoinColumn (name = "model")
	private @NonNull Model model;
	
	private String line;
}
