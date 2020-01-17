package com.mooveit.cars.domain;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter @Setter @RequiredArgsConstructor @ToString @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "UN_MODEL_NAME", columnNames = {"name"}))
public class Model {
	
	@Id
	@GeneratedValue(generator = "model_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "model_id_seq", sequenceName = "model_id_seq")
	private int id;

	@NonNull
	private String name;
	
	@ManyToOne
	@NonNull
	private Brand brand;
		
	private String type;
	private String line;
	private String fromYear;
	private String toYear;
	
	@Lob
	private byte[] photo;
	
	@ManyToOne
	@NonNull
	private Engine engine;
	
	@ManyToOne
	@NonNull
	private Wheels wheels;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentModel")
	private Set<Model> subModels = new HashSet<>();
	
	@ManyToOne
	private Model parentModel;
	
}
