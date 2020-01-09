package com.mooveit.cars.services;

import com.mooveit.cars.configurations.ConfigurationProperties;
import com.mooveit.cars.configurations.StaticApplicationContext;
import com.mooveit.cars.domain.CarModelEntity;
import com.mooveit.cars.domain.CatalogueEntity;
import com.mooveit.cars.domain.EngineEntity;
import com.mooveit.cars.domain.WheelEntity;
import com.mooveit.cars.exceptions.NoValidXmlFileException;
import com.mooveit.cars.interfaces.IngestInterface;
import com.mooveit.cars.repositories.CarModelRepository;
import com.mooveit.cars.repositories.CatalogueRepository;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.WheelRepository;
import com.mooveit.cars.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class FordXmlService implements IngestInterface {


    private static final String MODEL_XML_TAG = "MODEL";
    private static final String ENGINE_XML_TAG = "ENGINE";
    private static final String WHEELS_XML_TAG = "WHEELS";
    private static final String SUBMODELS_XML_TAG = "SUBMODELS";
    private HashMap<String, CarModelEntity> hashMapForCarModelProcessed = new HashMap<>();

    private ConfigurationProperties configurationProperties = (ConfigurationProperties) StaticApplicationContext.getContext()
            .getBean("configurationProperties");

    private CatalogueRepository catalogueRepository = (CatalogueRepository) StaticApplicationContext.getContext()
            .getBean("catalogueRepository");

    private EngineRepository engineRepository = (EngineRepository) StaticApplicationContext.getContext()
            .getBean("engineRepository");

    private WheelRepository wheelRepository = (WheelRepository) StaticApplicationContext.getContext()
            .getBean("wheelRepository");

    private CarModelRepository carModelRepository = (CarModelRepository) StaticApplicationContext.getContext()
            .getBean("carModelRepository");

    String carModelBrand = configurationProperties.getBrandNameFord();

    @Override
    public void ingestFile(File fileInput) {

        File fordXsdFile = Utils.readFile(configurationProperties.getInputXsdFordFile());
        if (isAValidXmlFileFormat(fileInput, fordXsdFile)) {
            hashMapForCarModelProcessed = new HashMap<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(fileInput);
                document.getDocumentElement().normalize();
                processFileAndGenerateAnCatalogue(document);
            } catch (SAXException | ParserConfigurationException | IOException e) {
                log.error("error parsing .xml input file");
                throw new NoValidXmlFileException("error parsing .xml input file ");
            }
        }
    }

    private boolean isAValidXmlFileFormat(File inputXmlFile, File inputXsdFile) {
        try {
            SchemaFactory xsdFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = xsdFactory.newSchema(inputXsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(inputXmlFile));
        } catch (SAXException | IOException e) {
            log.error("error validating .xml input file");
            throw new NoValidXmlFileException("error validating .xml input file", e);
        }
        return true;
    }


    private void processFileAndGenerateAnCatalogue(Document document) {

        CatalogueEntity catalogueEntity = new CatalogueEntity();
        catalogueEntity.setName("Ford Catalogue 2020");
        catalogueRepository.save(catalogueEntity);
        NodeList nodeList = document.getElementsByTagName(MODEL_XML_TAG);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                CarModelEntity carModelEntity = createCarModel(node, catalogueEntity);
                if (carModelEntity != null) {
                    carModelRepository.save(carModelEntity);
                }
            }
        }
    }


    private CarModelEntity createCarModel(Node node, CatalogueEntity catalogueEntity) {
        String carModelName;
        String carModelType;
        String carModelLine;
        Integer carModelFrom;
        Integer carModelTo;
        Integer enginePower;
        String engineType;
        String wheelSize;
        String wheelType;
        CarModelEntity carModelEntity = null;


        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element modelElem = (Element) node;
            carModelName = modelElem.getAttribute("name");
            if (!hashMapForCarModelProcessed.containsKey(carModelName)) {
                carModelEntity = new CarModelEntity();
                Node nodeEngine = modelElem.getElementsByTagName(ENGINE_XML_TAG).item(0);
                Node nodeWheel = modelElem.getElementsByTagName(WHEELS_XML_TAG).item(0);
                Node nodeSubModel = modelElem.getElementsByTagName(SUBMODELS_XML_TAG).item(0);
                Element engineElem = (Element) nodeEngine;
                Element wheelElem = (Element) nodeWheel;
                Element subModelElem = (Element) nodeSubModel;
                Optional<EngineEntity> optionalEngineByPowerAndType;
                Optional<WheelEntity> optionalWheelBySizeAndType;

                carModelType = modelElem.getAttribute("type");
                carModelLine = modelElem.getAttribute("line");
                engineType = engineElem.getAttribute("type");
                wheelSize = wheelElem.getAttribute("size");
                wheelType = wheelElem.getAttribute("type");
                carModelFrom = Strings.isNotEmpty(modelElem.getAttribute("from"))
                        ? Integer.valueOf(modelElem.getAttribute("from")) : null;
                carModelTo = Strings.isNotEmpty(modelElem.getAttribute("to"))
                        ? Integer.valueOf(modelElem.getAttribute("to")) : null;
                enginePower = Strings.isNotEmpty(engineElem.getAttribute("power"))
                        ? Integer.valueOf(engineElem.getAttribute("power")) : null;

                carModelEntity.setName(carModelName);
                carModelEntity.setFrom(carModelFrom);
                carModelEntity.setTo(carModelTo);
                carModelEntity.setType(carModelType);
                carModelEntity.setLine(carModelLine);
                carModelEntity.setBrand(carModelBrand);
                carModelEntity.setCatalogueEntity(catalogueEntity);

                optionalEngineByPowerAndType = engineRepository.findByPowerAndTypeAndIsActiveTrue(enginePower, engineType);
                optionalWheelBySizeAndType = wheelRepository.findBySizeAndTypeAndIsActiveTrue(wheelSize, wheelType);

                if (optionalEngineByPowerAndType.isPresent()) {
                    carModelEntity.setEngineEntity(optionalEngineByPowerAndType.get());
                } else {
                    carModelEntity.setEngineEntity(engineRepository.save(new EngineEntity(enginePower, engineType)));
                }

                if (optionalWheelBySizeAndType.isPresent()) {
                    carModelEntity.setWheelEntity(optionalWheelBySizeAndType.get());
                } else {
                    carModelEntity.setWheelEntity(wheelRepository.save(new WheelEntity(wheelSize, wheelType)));
                }


                if (subModelElem != null && subModelElem.hasChildNodes()) {
                    NodeList nodeList = subModelElem.getElementsByTagName(MODEL_XML_TAG);
                    Set<CarModelEntity> carModelsListForSubModelEntities = new HashSet<>();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node nodeSub = nodeList.item(i);
                        if (nodeSub.getNodeType() == Node.ELEMENT_NODE) {
                            CarModelEntity subCarModelEntity = createCarModel(nodeSub, catalogueEntity);
                            if (subCarModelEntity != null) {
                                subCarModelEntity.setParentModel(carModelEntity);
                                carModelsListForSubModelEntities.add(subCarModelEntity);
                            }
                        }
                    }
                    carModelEntity.setSubModels(carModelsListForSubModelEntities);
                }
                hashMapForCarModelProcessed.put(carModelName, carModelEntity);
            }
        }

        return carModelEntity;
    }

}
