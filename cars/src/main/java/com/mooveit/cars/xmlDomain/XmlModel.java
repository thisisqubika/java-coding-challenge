package com.mooveit.cars.xmlDomain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlModel {
    @XmlAttribute
    protected String  name;
    @XmlAttribute
    protected Integer from;
    @XmlAttribute
    protected Integer to;
    @XmlAttribute
    protected String type;
    @XmlElement(name = "ENGINE")
    protected XmlEngine engine;
    @XmlElement(name = "WHEELS")
    protected XmlWheels wheels;
    @XmlElementWrapper(name = "SUBMODELS")
    @XmlElement(name = "MODEL")
    private List<XmlSubModel> submodel;
}
