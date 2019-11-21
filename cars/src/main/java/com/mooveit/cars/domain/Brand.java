package com.mooveit.cars.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @RequiredArgsConstructor @ToString @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "UN_BRAND_NAME", columnNames = {"name"}))
public class Brand {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NonNull
	private String name;

}
