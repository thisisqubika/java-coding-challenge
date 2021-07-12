package com.mooveit.cars.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;

@Data
@XStreamAlias("WHEELS")
public class WheelsDto implements Serializable {
	private static final long serialVersionUID = 3599198042007085074L;
	@XStreamOmitField
	private Long id;
	@XStreamAsAttribute
	private String size;
	@XStreamAsAttribute
	private String type;

	public WheelsDto() {
	}

	public WheelsDto(String size, String type) {
		this.size = size;
		this.type = type;
	}

	@Override
	public String toString() {
		return "WheelsDto{" + "id=" + id + ", size='" + size + '\'' + ", type='" + type + '\'' + '}';
	}
}
