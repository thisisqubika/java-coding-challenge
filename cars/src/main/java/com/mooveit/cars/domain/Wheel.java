package com.mooveit.cars.domain;

import com.mooveit.cars.domain.enums.WheelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "wheels")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wheel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "size")
    private String size;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WheelTypeEnum type;
}
