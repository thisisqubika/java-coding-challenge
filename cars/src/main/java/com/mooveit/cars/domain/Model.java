package com.mooveit.cars.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
public class Model extends BaseModel{

	@ManyToOne
    @JoinColumn (name = "brand")
	private @NonNull Brand brand;
	private @NonNull String type;
	@OneToMany
	private Set<SubModel> subModels;
}
