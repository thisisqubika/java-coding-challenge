package com.mooveit.cars.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ConfigurationProperties {

    @Value("${input.file.ford.xml}")
    private String inputFordFile;

    @Value("${input.file.ford.xsd}")
    private String inputXsdFordFile;

    @Value("${input.file.ford.brandName}")
    private String brandNameFord;

    @Value("${cars.ford.ingester.runCron}")
    private String fordIngesterCronTrigger;

}
