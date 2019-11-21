package com.mooveit.cars.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @RequiredArgsConstructor @ToString @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "UN_POWER_TYPE", columnNames = {"power", "type"}))
public class Engine {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NonNull
	private String power;
	
	@NonNull
	private String type;
	
}
