package com.mooveit.cars.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "FILES_PROCESSED")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileProcessed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename")
    private String name;

    @Column(name = "processed_date")
    private Date processedDate;
}
