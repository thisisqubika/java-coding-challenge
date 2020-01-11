package com.mooveit.cars.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @RequiredArgsConstructor @ToString @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "UN_BRAND_NAME", columnNames = {"name"}))
public class Brand {

	@Id
	@GeneratedValue(generator = "brand_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "brand_id_seq", sequenceName = "brand_id_seq")
	private int id;
	
	@NonNull
	private String name;

}
