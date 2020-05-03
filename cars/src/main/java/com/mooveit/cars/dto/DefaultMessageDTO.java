package com.mooveit.cars.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DefaultMessageDTO {

	private String message;
	private boolean error;
	
	public DefaultMessageDTO(String message, boolean error) {
		super();
		this.message = message;
		this.error = error;
	}	
	
}
