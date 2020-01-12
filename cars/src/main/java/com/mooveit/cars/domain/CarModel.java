package com.mooveit.cars.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;


@Entity
@Transactional
@XmlRootElement(name = "MODEL")
@Table(name = "CAR_MODEL")
public class CarModel extends Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<CarSubModel> subModels;

	@XmlElement(name = "MODEL")
	@XmlElementWrapper(name = "SUBMODELS")
	@OneToMany(targetEntity=CarSubModel.class,mappedBy = "model",fetch=FetchType.EAGER)
	public List<CarSubModel> getSubModels() {
		return subModels;
	}

	public void setSubModels(List<CarSubModel> subModels) {
		this.subModels = subModels;
	}

	
}
