package com.mooveit.cars.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.Catalogue;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModels;
import com.mooveit.cars.domain.Wheels;
import com.mooveit.cars.dto.CarBrandDto;
import com.mooveit.cars.dto.CatalogueDto;
import com.mooveit.cars.dto.EngineDto;
import com.mooveit.cars.dto.ModelDto;
import com.mooveit.cars.dto.SubModelsDto;
import com.mooveit.cars.dto.WheelsDto;

public final class ConverterHelper {
	private ConverterHelper() {}

	//carBrand
	public static CarBrandDto toCarBrandDto(CarBrand carBrand) {
		if(Objects.nonNull(carBrand.getCatalogue())) {
			return new CarBrandDto(carBrand.getName(), ConverterHelper.toCatalogueDto(carBrand.getCatalogue()));
		}
		return null;
	}

	public static CarBrand fromCarBrandDto(CarBrandDto dto) {
		if(Objects.nonNull(dto.getCatalogueDto())) {
			return new CarBrand(dto.getName(), ConverterHelper.fromCatalogueDto(dto.getCatalogueDto()));
		}
		return null;
	}

	//catalogue
	public static CatalogueDto toCatalogueDto(Catalogue catalogue) {
		List<ModelDto> modelDtos = null;
		if(CollectionUtils.isNotEmpty(catalogue.getModels())) {
			modelDtos = catalogue.getModels().stream().map(ConverterHelper::toModelDto).collect(Collectors.toList());
		}
		CatalogueDto dto = new CatalogueDto(Objects.nonNull(modelDtos) ? modelDtos : new ArrayList<>());
		dto.setId(catalogue.getId());
		return dto;
	}

	public static Catalogue fromCatalogueDto(CatalogueDto dto) {
		List<Model> models = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(dto.getModelsDtos())) {
			models = dto.getModelsDtos().stream().map(ConverterHelper::fromModelDto).collect(Collectors.toList());
		}
		Catalogue catalogue = new Catalogue(models);
		catalogue.setId(dto.getId());
		return catalogue;
	}

	//model
	public static ModelDto toModelDto(Model model) {
		ModelDto dto = new ModelDto();
		dto.setId(model.getId());
		dto.setFrom(model.getFrom());
		dto.setTo(model.getTo());
		dto.setName(model.getName());
		dto.setLine(model.getLine());
		dto.setType(model.getType());
		if(Objects.nonNull(model.getEngine())) {
			dto.setEngineDto(ConverterHelper.toEngineDto(model.getEngine()));
		}
		if(Objects.nonNull(model.getWheels())) {
			dto.setWheelsDto(ConverterHelper.toWheelsDto(model.getWheels()));
		}
		if(Objects.nonNull(model.getSubModels())) {
			dto.setSubModelsDto(ConverterHelper.toSubModelsDto(model.getSubModels()));
		}
		return dto;
	}

	public static ModelDto toSubModelsModelDto(Model model) {
		ModelDto dto = new ModelDto();
		dto.setId(model.getId());
		dto.setFrom(model.getFrom());
		dto.setTo(model.getTo());
		dto.setName(model.getName());
		dto.setLine(model.getLine());
		dto.setType(model.getType());
		if(Objects.nonNull(model.getEngine())) {
			dto.setEngineDto(ConverterHelper.toEngineDto(model.getEngine()));
		}
		if(Objects.nonNull(model.getWheels())) {
			dto.setWheelsDto(ConverterHelper.toWheelsDto(model.getWheels()));
		}
		return dto;
	}

	public static Model fromModelDto(ModelDto dto) {
		Model model = new Model();
		model.setId(dto.getId());
		model.setFrom(dto.getFrom());
		model.setTo(dto.getTo());
		model.setName(dto.getName());
		model.setLine(dto.getLine());
		model.setType(dto.getType());
		if(Objects.nonNull(dto.getEngineDto())) {
			model.setEngine(ConverterHelper.fromEngineDto(dto.getEngineDto()));
		}
		if(Objects.nonNull(dto.getWheelsDto())) {
			model.setWheels(ConverterHelper.fromWheelsDto(dto.getWheelsDto()));
		}
		if(Objects.nonNull(dto.getSubModelsDto())) {
			model.setSubModels(ConverterHelper.fromSubModelsDto(dto.getSubModelsDto()));
		}
		return model;
	}

	//engine
	public static EngineDto toEngineDto(Engine engine) {
		EngineDto dto = new EngineDto(engine.getPower(), engine.getType());
		dto.setId(engine.getId());
		return dto;
	}

	public static Engine fromEngineDto(EngineDto dto) {
		Engine engine = new Engine(dto.getPower(), dto.getType());
		engine.setId(dto.getId());
		return engine;
	}

	//wheels
	public static WheelsDto toWheelsDto(Wheels wheels) {
		WheelsDto dto = new WheelsDto(wheels.getSize(), wheels.getType());
		dto.setId(wheels.getId());
		return dto;
	}

	public static Wheels fromWheelsDto(WheelsDto dto) {
		Wheels wheels = new Wheels(dto.getSize(), dto.getType());
		wheels.setId(dto.getId());
		return wheels;
	}

	//subModels
	public static SubModelsDto toSubModelsDto(SubModels subModels) {
		List<ModelDto> modelDtos = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(subModels.getModels())) {
			modelDtos = subModels.getModels().stream().map(ConverterHelper::toSubModelsModelDto).collect(Collectors.toList());
		}
		SubModelsDto dto = new SubModelsDto(modelDtos);
		dto.setId(subModels.getId());
		return dto;
	}


	public static SubModels fromSubModelsDto(SubModelsDto dto) {
		List<Model> models = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(dto.getModelsDtos())) {
			models = dto.getModelsDtos().stream().map(ConverterHelper::fromModelDto).collect(Collectors.toList());
		}
		SubModels subModels = new SubModels(models);
		subModels.setId(dto.getId());
		return subModels;
	}
}
