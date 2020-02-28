package com.mooveit.cars.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class SubModel extends BaseModel{

    private String line;
    @JoinColumn
    @ManyToOne
    private Model model;
}
