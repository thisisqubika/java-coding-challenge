package com.mooveit.cars.utils.impl;

import com.mooveit.cars.domain.*;
import com.mooveit.cars.domain.enums.EngineTypeEnum;
import com.mooveit.cars.domain.enums.ModelLineEnum;
import com.mooveit.cars.domain.enums.ModelTypeEnum;
import com.mooveit.cars.domain.enums.WheelTypeEnum;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.FileProcessedRepository;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.repositories.WheelRepository;
import com.mooveit.cars.utils.XmlMapperAdapter;
import com.mooveit.cars.utils.XmlProcessor;
import com.mooveit.cars.utils.xmlmodels.CatalogXmlDTO;
import com.mooveit.cars.utils.xmlmodels.ModelXmlDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class XmlProcessorImpl implements XmlProcessor {

    @Autowired
    private XmlMapperAdapter xmlAdapter;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private WheelRepository wheelRepository;

    @Autowired
    private FileProcessedRepository fileProcessedRepository;


    @Override
    public void processFile(File xmlFile, Brand brand) {

        FileProcessed fileProcessed = fileProcessedRepository.findByName(xmlFile.getName());
        if (nonNull(fileProcessed)) {
            log.info(String.format("File %s was already processed %s", xmlFile.getName(), fileProcessed.getProcessedDate().toString()));
            return;
        }

        XMLStreamReader streamReaderReference = null;

        XMLStreamReader streamReader = (streamReaderReference = xmlAdapter.createStreamReader(xmlFile.toURI()));
        CatalogXmlDTO catalog = xmlAdapter.readValue(streamReader, CatalogXmlDTO.class);

        List<Model> submodels = new ArrayList<>();
        catalog.getModels().stream().forEach(model -> {
            Model parentModel = processModel(model, null, brand);
            submodels.addAll(model.getSubModels().stream().map(subModel -> processModel(subModel, parentModel.getId(), brand)).collect(Collectors.toList()));
        });

        modelRepository.saveAll(submodels);

        xmlAdapter.disposeStreamReader(streamReaderReference);

        fileProcessedRepository.save(FileProcessed.builder().name(xmlFile.getName()).processedDate(new Date()).build());

        log.info(String.format("File %s processed", xmlFile.getName()));
    }

    private Model processModel(ModelXmlDTO model, Long parentId, Brand brand) {
        try {
            Engine engine = engineRepository.findByPowerAndType(model.getEngineXmlDTO().getPower(), EngineTypeEnum.valueOf(model.getEngineXmlDTO().getType()));
            if (isNull(engine))
                engine = engineRepository.save(Engine.builder().power(model.getEngineXmlDTO().getPower()).type(nonNull(model.getEngineXmlDTO()) && nonNull(model.getEngineXmlDTO().getType()) ? EngineTypeEnum.valueOf(model.getEngineXmlDTO().getType()) : null).build());

            Wheel wheel = wheelRepository.findBySizeAndType(model.getWheelXmlDTO().getSize(), WheelTypeEnum.valueOf(model.getWheelXmlDTO().getType()));
            if (isNull(wheel))
                wheel = wheelRepository.save(Wheel.builder().size(nonNull(model.getWheelXmlDTO()) ? model.getWheelXmlDTO().getSize() : null).type(nonNull(model.getWheelXmlDTO()) && nonNull(model.getWheelXmlDTO().getType()) ? WheelTypeEnum.valueOf(model.getWheelXmlDTO().getType()) : null).build());

            return nonNull(parentId) ? getBuildModel(model, parentId, engine, wheel, brand) : modelRepository.save(getBuildModel(model, parentId, engine, wheel, brand));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Model getBuildModel(ModelXmlDTO model, Long parentId, Engine engine, Wheel wheel, Brand brand) throws ParseException {
        return Model.builder()
                .name(model.getName())
                .from(nonNull(model.getFrom()) ? new SimpleDateFormat("yyyy").parse(String.valueOf(model.getFrom())) : null)
                .to(nonNull(model.getTo()) ? new SimpleDateFormat("yyyy").parse(String.valueOf(model.getTo())) : null)
                .type(nonNull(model.getType()) ? Arrays.stream(ModelTypeEnum.values()).filter(type -> type.getDescription().equals(model.getType())).findAny().get() : null)
                .line(nonNull(model.getLine()) ? Arrays.stream(ModelLineEnum.values()).filter(line -> line.getDescription().equals(model.getLine())).findAny().get() : null)
                .engine(engine)
                .wheel(wheel)
                .parent(nonNull(parentId) ? Model.builder().id(parentId).build() : null)
                .brand(brand)
                .build();
    }
}
