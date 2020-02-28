package com.mooveit.cars.xmlDomain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "CATALOGUE")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCatalogue {
    private String name;
    @XmlElement(name = "MODEL")
    private List<XmlModel> model;
}
