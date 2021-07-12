package com.mooveit.cars.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;

@Data
@XStreamAlias("ENGINE")
public class EngineDto implements Serializable {
	private static final long serialVersionUID = -4400283264470260421L;
	@XStreamOmitField
	private Long id;
	@XStreamAsAttribute
	private String power;
	@XStreamAsAttribute
	private String type;

	public EngineDto() {
	}

	public EngineDto(String power, String type) {
		this.power = power;
		this.type = type;
	}

	@Override
	public String toString() {
		return "EngineDto{" + "id=" + id + ", power='" + power + '\'' + ", type='" + type + '\'' + '}';
	}
}
