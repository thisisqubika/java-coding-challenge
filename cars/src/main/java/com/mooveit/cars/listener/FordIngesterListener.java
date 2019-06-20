package com.mooveit.cars.listener;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mooveit.cars.services.interfaces.IModelService;
import com.mooveit.cars.acl.xmlmodel.Catalogue;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

@Component
public class FordIngesterListener {

    private static final Logger logger = LoggerFactory.getLogger(FordIngesterListener.class);

    @Autowired
    private IModelService modelService;

    @Autowired
    private ApplicationContext context;

    @Value("classpath:ford-example.xml")
    private Resource fileResource;

    @EventListener(ApplicationReadyEvent.class)
    public Boolean onApplicationEvent() {
        return parseFile().flatMap(this::createSchema)
                .andThen(() -> logger.info("Schema created!!!"))
                .getOrElseGet(error -> {
                    error.printStackTrace();
                    logger.error("An error was ocurred initializing the DB", error);
                    SpringApplication.exit(context);
                    return false;
                });
    }

    private Try<Catalogue> parseFile() {
        return Try.of(() -> {
            final String xmlSchema = new String(Files.readAllBytes(fileResource.getFile().toPath()));
            XmlMapper mapper = new XmlMapper();
            mapper.setDefaultUseWrapper(false);
            return mapper.readValue(xmlSchema, Catalogue.class);
        });
    }

    private Try<Boolean> createSchema(Catalogue catalogue) {
        return modelService.save(catalogue);
    }
}
