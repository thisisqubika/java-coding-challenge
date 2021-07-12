package com.mooveit.cars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "catalogue")
public class Catalogue implements Serializable {
	private static final long serialVersionUID = -4692794045742673878L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "catalogue_id", referencedColumnName = "id")
	private List<Model> models = new ArrayList<>();

	public Catalogue() {
	}

	public Catalogue(List<Model> models) {
		if (Objects.isNull(models)) {
			this.models = new ArrayList<>();
		} else {
			this.models = models;
		}
	}

	public void addModel(Model model) {
		this.models.add(model);
	}

	@Override
	public String toString() {
		return "Catalogue{" + "models=" + models + '}';
	}

}
