package com.mooveit.cars.catalogue;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@XmlRootElement
public class Submodels {

	private List<Submodel> submodels = new ArrayList<Submodel>();

	@XmlElement(name = "MODEL")
	public List<Submodel> getSubmodels() {
		return submodels;
	}

	public void setSubmodels(final List<Submodel> submodels) {
		this.submodels = submodels;
	}

}
