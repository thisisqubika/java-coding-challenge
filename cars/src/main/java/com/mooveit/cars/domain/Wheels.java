package com.mooveit.cars.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @RequiredArgsConstructor @ToString @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "UN_SIZE_TYPE", columnNames = {"size", "type"}))
public class Wheels {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NonNull
	private String size;
	
	@NonNull
	private String type;

}
