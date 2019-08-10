package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The entity to model a car's wheels for each {@link AbstractSpec}.
 * <br>
 * Wheels with the same `size` and `type` will be unique.
*/
@Entity
@Table(name = "wheel")
public class Wheel extends BaseEntity {

    @NotNull
    private String size;

    @NotNull
    private String type;

    protected Wheel() {
    	super();
    }

    public Wheel(String size, String type) {
    	super();
        this.size = size;
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

	public void setSize(String size) {
		this.size = size;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
