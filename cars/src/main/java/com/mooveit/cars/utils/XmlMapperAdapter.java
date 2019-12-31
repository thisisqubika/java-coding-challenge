package com.mooveit.cars.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Objects.nonNull;

@Component
public class XmlMapperAdapter {
    private final XmlMapper xmlMapper;
    private final XMLInputFactory xmlInputFactory;

    public XmlMapperAdapter() {
        this.xmlMapper = Jackson2ObjectMapperBuilder.xml()
                .modules(new JaxbAnnotationModule(), new JavaTimeModule())
                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                .build();
        this.xmlInputFactory = XMLInputFactory.newFactory();
    }


    public XMLStreamReader createStreamReader(URI source) {
        try {
            return xmlInputFactory.createXMLStreamReader(Files.newBufferedReader(Paths.get(source), Charset.forName("ISO-8859-1")));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void disposeStreamReader(XMLStreamReader streamReader) {
        try {
            if (nonNull(streamReader)) {
                streamReader.close();
            }
        }catch (XMLStreamException se){
            se.printStackTrace();
        }
    }

    public <T> T readValue(XMLStreamReader r, Class<T> valueType) {
        try {
            return xmlMapper.readValue(r, valueType);
        }catch (IOException io){
            io.printStackTrace();
        }
        return null;
    }
}
