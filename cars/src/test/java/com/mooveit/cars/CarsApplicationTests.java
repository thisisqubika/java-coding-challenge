package com.mooveit.cars;

import com.mooveit.cars.configurations.ConfigurationProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarsApplicationTests {

    @Autowired
    private ConfigurationProperties configurationFileProperties;

    @Test
    public void contextLoads() {
    }

    @DisplayName("a Ford input .XSM file can be read")
    @Test
    void aFordInputXmlFileCanBeRead() {
        File xmlFile = readFile(configurationFileProperties.getInputFordFile());
        assertThat(xmlFile).isNotNull();
    }


    @DisplayName("a Ford input .XSD file can be read")
    @Test
    void aFordInputXsdFileCanBeRead() {
        File xsdFile = readFile(configurationFileProperties.getInputXsdFordFile());
        assertThat(xsdFile).isNotNull();
    }


    @DisplayName("validating a xml file against a xsd file")
    @Test
    void validatingAXmlFileAgainstAXsdFile() {
        boolean testResult = true;
        File xmlFile = readFile(configurationFileProperties.getInputFordFile());
        File xsdFile = readFile(configurationFileProperties.getInputXsdFordFile());
        assertThat(xmlFile).isNotNull();
        assertThat(xsdFile).isNotNull();

        try {
            SchemaFactory xsdFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = xsdFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            testResult = false;
        }

        assertThat(testResult).isTrue();
    }

    private File readFile(String inputFilePath) {
        File file = null;
        try {
            synchronized (inputFilePath) {
                file = ResourceUtils.getFile("classpath:" + inputFilePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

}
