package com.mooveit.cars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sub_models")
public class SubModels implements Serializable {
	private static final long serialVersionUID = 6805234243952876176L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "subModels")
	private List<Model> models = new ArrayList<>();;

	public SubModels() {
	}

	public SubModels(List<Model> models) {
		if (Objects.isNull(models)) {
			this.models = new ArrayList<>();
		} else {
			this.models = models;
		}
	}

	@Override
	public String toString() {
		return "SubModels{" + "models=" + models + '}';
	}
}
