package com.mooveit.cars.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CarBrandDto implements Serializable {
	private static final long serialVersionUID = 370012978057179686L;
	private Long id;
	private String name;
	private CatalogueDto catalogueDto;

	public CarBrandDto() {
	}

	public CarBrandDto(String name, CatalogueDto catalogueDto) {
		this.name = name;
		this.catalogueDto = catalogueDto;
	}

	@Override
	public String toString() {
		return "CarBrandDto{" + "id=" + id + ", name='" + name + '\'' + ", catalogueDto=" + catalogueDto + '}';
	}
}
