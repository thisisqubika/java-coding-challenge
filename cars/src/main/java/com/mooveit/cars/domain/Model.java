package com.mooveit.cars.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @RequiredArgsConstructor @ToString @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "UN_MODEL_NAME", columnNames = {"name"}))
public class Model {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@NonNull
	private Brand brand;
	
	@NonNull
	private String name;
		
	private String type;
	private String line;
	private int fromYear;
	private int toYear;
	
	@ManyToOne
	@NonNull
	private Engine engine;
	
	@ManyToOne
	@NonNull
	private Wheels wheels;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Model> subModels = new HashSet<>();
	
	@ManyToOne
	private Model parentModel;
	
}
