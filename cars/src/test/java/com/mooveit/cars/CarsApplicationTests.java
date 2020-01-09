package com.mooveit.cars;

import com.mooveit.cars.assemblers.CarModelAssembler;
import com.mooveit.cars.configurations.ConfigurationProperties;
import com.mooveit.cars.controllers.CarModelController;
import com.mooveit.cars.domain.CarModelEntity;
import com.mooveit.cars.domain.EngineEntity;
import com.mooveit.cars.domain.WheelEntity;
import com.mooveit.cars.models.CarModelModel;
import com.mooveit.cars.models.EngineModel;
import com.mooveit.cars.models.WheelModel;
import com.mooveit.cars.services.CarModelService;
import com.mooveit.cars.services.ConvertBetweenEntitiesAndModels;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarsApplicationTests {

    @MockBean
    private CarModelService carModelService;

    @MockBean
    private CarModelController carModelController;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ConfigurationProperties configurationFileProperties;


    @Autowired
    private CarModelAssembler carModelAssembler;

    @Autowired
    private PagedResourcesAssembler<CarModelEntity> pagedResourcesAssembler;

    @Autowired
    private ConvertBetweenEntitiesAndModels convertBetweenEntitiesAndModels;

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


    @DisplayName("endpoint /api/v1/car/{id} test")
    @Test
    void whenCallFindCarByIdEndPoint_thenReturnAValidHttpStatus() throws Exception {
        //  HttpStatus.NOT_FOUND
        when(carModelController.findCarModelById(100L)).thenReturn(ResponseEntity.notFound().build());
        mvc.perform(get("/api/v1/cars/100")).andExpect(status().isNotFound());


        // HttpStatus.BAD_REQUEST
        when(carModelController.findCarModelById(100L)).thenReturn(ResponseEntity.badRequest().build());
        mvc.perform(get("/api/v1/cars/aa")).andExpect(status().isBadRequest());


        // HttpStatus.OK
        CarModelModel carModelModel = createACarModelModel();
        when(carModelController.findCarModelById(100L)).thenReturn(ResponseEntity.ok().body(carModelModel));
        mvc.perform(get("/api/v1/cars/100")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Aspire")));

    }


    @DisplayName("endpoint /api/v1/car/brand/{brand} test")
    @Test
    void whenCallFindCarByBrandEndPoint_thenReturnAValidHttpStatus() throws Exception {
        //  HttpStatus.NOT_FOUND
        when(carModelController.findAllCarModelByBrand("test_only", PageRequest.of(0, 20))).thenReturn(ResponseEntity.notFound().build());
        mvc.perform(get("/api/v1/cars/brand/test_only")).andExpect(status().isNotFound());


        // HttpStatus.OK
        CarModelEntity carModelEntity1 = createACarModelEntity();
        CarModelEntity carModelEntity2 = createACarModelEntity();
        CarModelEntity carModelEntity3 = createACarModelEntity();
        List<CarModelEntity> carModelList = new ArrayList<>();
        carModelList.add(carModelEntity1);
        carModelList.add(carModelEntity2);
        carModelList.add(carModelEntity3);

        Page<CarModelEntity> pages = new PageImpl<CarModelEntity>(carModelList);
        PagedModel<CarModelModel> carModelModelList;
        carModelModelList = pagedResourcesAssembler.toModel(pages, carModelAssembler);

        when(carModelController.findAllCarModelByBrand("100", PageRequest.of(0, 20))).thenReturn(ResponseEntity.ok().body(carModelModelList));
        mvc.perform(get("/api/v1/cars/brand/100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements", equalTo(3)));


    }

    private CarModelModel createACarModelModel() {
        CarModelModel carModelModel = new CarModelModel();
        carModelModel.setId(20L);
        carModelModel.setBrand("Ford");
        carModelModel.setName("Aspire");
        carModelModel.setParentName("");
        carModelModel.setFrom(1994);
        carModelModel.setTo(1997);
        carModelModel.setType("sport-utility");
        carModelModel.setLine("compact minivan");
        carModelModel.setEngine(new EngineModel(1600, "GAS"));
        carModelModel.setWheel(new WheelModel("R16", "ALLOY"));
        return carModelModel;
    }


    private CarModelEntity createACarModelEntity() {
        CarModelEntity carModelEntity = new CarModelEntity();
        carModelEntity.setId(20L);
        carModelEntity.setBrand("Ford");
        carModelEntity.setName("Aspire");
        carModelEntity.setParentModel(null);
        carModelEntity.setFrom(1994);
        carModelEntity.setTo(1997);
        carModelEntity.setType("sport-utility");
        carModelEntity.setLine("compact minivan");
        carModelEntity.setEngineEntity(new EngineEntity(1600, "GAS"));
        carModelEntity.setWheelEntity(new WheelEntity("R16", "ALLOY"));
        return carModelEntity;
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
