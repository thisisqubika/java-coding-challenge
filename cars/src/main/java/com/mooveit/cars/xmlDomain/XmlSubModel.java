package com.mooveit.cars.xmlDomain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement(name = "MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSubModel {
    @XmlAttribute
    protected String  name;
    @XmlAttribute
    protected Integer from;
    @XmlAttribute
    protected Integer to;
    @XmlAttribute
    protected String line;
    @XmlElement(name = "ENGINE")
    protected XmlEngine engine;
    @XmlElement(name = "WHEELS")
    protected XmlWheels wheels;
}
