package com.mooveit.cars.services;

import com.mooveit.cars.domain.*;
import com.mooveit.cars.repositories.*;
import com.mooveit.cars.xmlDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class XmlService {

    @Autowired
    private CatalogueRepository catalogueRepository;
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private SubModelRepository subModelRepository;
    @Autowired
    private WheelsRepository wheelsRepository;


    public void persistXmlCatalogue(XmlCatalogue xmlCatalogue){
        Catalogue catalogue = saveCatalogue(xmlCatalogue);
        saveModelAndSubModel(xmlCatalogue, catalogue);
    }

    private Catalogue saveCatalogue(XmlCatalogue xmlCatalogue) {
        Optional<Catalogue> catalogueOptional = catalogueRepository.findById(xmlCatalogue.getName());
        Catalogue catalogue;
        if(catalogueOptional.isPresent()){
            catalogue=catalogueOptional.get();
            //removeModelAndSubModel(catalogue);
        }else{
            catalogue = new Catalogue();
            catalogue.setName(xmlCatalogue.getName());
            catalogue = catalogueRepository.save(catalogue);
        }
        return catalogue;
    }

    private void saveModelAndSubModel(XmlCatalogue xmlCatalogue, Catalogue catalogue) {
        saveUpdateDeleteModel(xmlCatalogue.getModel(), catalogue);
    }

    private void saveUpdateDeleteModel(List<XmlModel> xmlModelList, Catalogue catalogue) {
        List<Model> modelList = modelRepository.findByCatalogue(catalogue);
        if(modelList.isEmpty()){
            saveModel(xmlModelList, catalogue);
            return;
        }
        List<XmlModel> xmlModelsUpdate = new ArrayList<>();
        List<XmlModel> xmlModelsCreate = new ArrayList<>();
        List<Model> modelsUpdate = new ArrayList<>();
        List<Model> modelsDelete = new ArrayList<>();
        modelList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        xmlModelList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        int i=0;
        int j=0;
        while(i < modelList.size() && j < xmlModelList.size()){
                if(modelList.get(i).getName().compareTo(xmlModelList.get(j).getName())>0){
                    xmlModelsCreate.add(xmlModelList.get(j));
                    j++;
                }else if(modelList.get(i).getName().compareTo(xmlModelList.get(j).getName())<0){
                    modelsDelete.add(modelList.get(i));
                    i++;
                }else{
                    modelsUpdate.add(modelList.get(i));
                    xmlModelsUpdate.add(xmlModelList.get(j));
                    i++;
                    j++;
                }
        }
        while (i < modelList.size()){
            modelsDelete.add(modelList.get(i));
            i++;
        }
        while (j < xmlModelList.size()){
            xmlModelsCreate.add(xmlModelList.get(j));
            j++;
        }
        saveModel(xmlModelsCreate, catalogue);
        deleteModel(modelsDelete);
        updateModel(modelsUpdate, xmlModelsUpdate);
    }

    private void saveModel(List<XmlModel> modelList, Catalogue catalogue) {
        for (XmlModel xmlModel : modelList){
            Model model = new Model();
            Engine engine = getEngine(xmlModel.getEngine());
            Wheels wheels = getWheels(xmlModel.getWheels());
            model.setCatalogue(catalogue);
            model.setType(xmlModel.getType());
            model.setName(xmlModel.getName());
            model.setYearTo(xmlModel.getTo());
            model.setYearFrom(xmlModel.getFrom());
            model.setEngine(engine);
            model.setWheels(wheels);
            model = modelRepository.save(model);
            saveSubmodel(xmlModel.getSubmodel(), model);
        }
    }

    private void deleteModel(List<Model> modelsDelete) {
        modelsDelete.forEach(m->{
            subModelRepository.deleteByModel(m);
            modelRepository.delete(m);
        });
    }

    private void updateModel(List<Model> modelsUpdate, List<XmlModel> xmlModelsUpdate) {
        for(int i=0; i<modelsUpdate.size(); i++){
            boolean update = false;
            if(!modelsUpdate.get(i).getType().equals(xmlModelsUpdate.get(i).getType())){
                modelsUpdate.get(i).setType(xmlModelsUpdate.get(i).getType());
                update=true;
            }
            if(modelsUpdate.get(i).getYearFrom()==null && xmlModelsUpdate.get(i).getFrom()!=null
                || modelsUpdate.get(i).getYearFrom()!=null && xmlModelsUpdate.get(i).getFrom()==null
                    || !(modelsUpdate.get(i).getYearFrom()!=null && xmlModelsUpdate.get(i).getFrom()!=null)
                    || !modelsUpdate.get(i).getYearFrom().equals(xmlModelsUpdate.get(i).getFrom())){
                modelsUpdate.get(i).setYearFrom(xmlModelsUpdate.get(i).getFrom());
                update=true;
            }
            if(modelsUpdate.get(i).getYearTo()==null && xmlModelsUpdate.get(i).getTo()!=null
                    || modelsUpdate.get(i).getYearTo()!=null && xmlModelsUpdate.get(i).getTo()==null
                    || !(modelsUpdate.get(i).getYearTo()!=null && xmlModelsUpdate.get(i).getTo()!=null)
                    || !modelsUpdate.get(i).getYearTo().equals(xmlModelsUpdate.get(i).getTo())){
                modelsUpdate.get(i).setYearTo(xmlModelsUpdate.get(i).getTo());
                update=true;
            }
            if(!modelsUpdate.get(i).getEngine().getPower().equals(xmlModelsUpdate.get(i).getEngine().getPower())
            || !modelsUpdate.get(i).getEngine().getType().equals(xmlModelsUpdate.get(i).getEngine().getType())){
                Engine engine = getEngine(xmlModelsUpdate.get(i).getEngine());
                modelsUpdate.get(i).setEngine(engine);
                update=true;
            }
            if(!modelsUpdate.get(i).getWheels().getSize().equals(xmlModelsUpdate.get(i).getWheels().getSize())
                    || !modelsUpdate.get(i).getWheels().getType().equals(xmlModelsUpdate.get(i).getWheels().getType())){
                Wheels wheels = getWheels(xmlModelsUpdate.get(i).getWheels());
                modelsUpdate.get(i).setWheels(wheels);
                update=true;
            }
            if(update){
                modelRepository.save(modelsUpdate.get(i));
            }
            saveUpdateDeleteSubModel(xmlModelsUpdate.get(i).getSubmodel(), modelsUpdate.get(i));
        }
    }

    private void saveUpdateDeleteSubModel(List<XmlSubModel> xmlSubModelList, Model model) {
        List<SubModel> subModelList = subModelRepository.findByModel(model);
        List<XmlSubModel> xmlSubModelsUpdate = new ArrayList<>();
        List<XmlSubModel> xmlSubModelsCreate = new ArrayList<>();
        List<SubModel> subModelsUpdate = new ArrayList<>();
        List<SubModel> subModelsDelete = new ArrayList<>();
        subModelList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        xmlSubModelList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        int i=0;
        int j=0;
        while(i < subModelList.size() && j < xmlSubModelList.size()){
            if(subModelList.get(i).getName().compareTo(xmlSubModelList.get(j).getName())>0){
                xmlSubModelsCreate.add(xmlSubModelList.get(j));
                j++;
            }else if(subModelList.get(i).getName().compareTo(xmlSubModelList.get(j).getName())<0){
                subModelsDelete.add(subModelList.get(i));
                i++;
            }else{
                subModelsUpdate.add(subModelList.get(i));
                xmlSubModelsUpdate.add(xmlSubModelList.get(j));
                i++;
                j++;
            }
        }
        while (i < subModelList.size()){
            subModelsDelete.add(subModelList.get(i));
            i++;
        }
        while (j < xmlSubModelList.size()){
            xmlSubModelsCreate.add(xmlSubModelList.get(j));
            j++;
        }
        saveSubmodel(xmlSubModelsCreate, model);
        deleteSubModel(subModelsDelete);
        updateSubModel(subModelsUpdate, xmlSubModelsUpdate);
    }

    private void deleteSubModel(List<SubModel> subModelsDelete) {
        subModelsDelete.forEach(sm->subModelRepository.delete(sm));
    }

    private void updateSubModel(List<SubModel> subModelsUpdate, List<XmlSubModel> xmlSubModelsUpdate) {
        for(int i=0; i<subModelsUpdate.size(); i++){
            boolean update = false;
            if(!subModelsUpdate.get(i).getLine().equals(xmlSubModelsUpdate.get(i).getLine())){
                subModelsUpdate.get(i).setLine(xmlSubModelsUpdate.get(i).getLine());
                update=true;
            }
            if(subModelsUpdate.get(i).getYearFrom()==null && xmlSubModelsUpdate.get(i).getFrom()!=null
                    || subModelsUpdate.get(i).getYearFrom()!=null && xmlSubModelsUpdate.get(i).getFrom()==null
                    || !(subModelsUpdate.get(i).getYearFrom()!=null && xmlSubModelsUpdate.get(i).getFrom()!=null)
                    || !subModelsUpdate.get(i).getYearFrom().equals(xmlSubModelsUpdate.get(i).getFrom())){
                subModelsUpdate.get(i).setYearFrom(xmlSubModelsUpdate.get(i).getFrom());
                update=true;
            }
            if(subModelsUpdate.get(i).getYearTo()==null && xmlSubModelsUpdate.get(i).getTo()!=null
                    || subModelsUpdate.get(i).getYearTo()!=null && xmlSubModelsUpdate.get(i).getTo()==null
                    || !(subModelsUpdate.get(i).getYearTo()!=null && xmlSubModelsUpdate.get(i).getTo()!=null)
                    || !subModelsUpdate.get(i).getYearTo().equals(xmlSubModelsUpdate.get(i).getTo())){
                subModelsUpdate.get(i).setYearTo(xmlSubModelsUpdate.get(i).getTo());
                update=true;
            }
            if(!subModelsUpdate.get(i).getEngine().getPower().equals(xmlSubModelsUpdate.get(i).getEngine().getPower())
                    || !subModelsUpdate.get(i).getEngine().getType().equals(xmlSubModelsUpdate.get(i).getEngine().getType())){
                Engine engine = getEngine(xmlSubModelsUpdate.get(i).getEngine());
                subModelsUpdate.get(i).setEngine(engine);
                update=true;
            }
            if(!subModelsUpdate.get(i).getWheels().getSize().equals(xmlSubModelsUpdate.get(i).getWheels().getSize())
                    || !subModelsUpdate.get(i).getWheels().getType().equals(xmlSubModelsUpdate.get(i).getWheels().getType())){
                Wheels wheels = getWheels(xmlSubModelsUpdate.get(i).getWheels());
                subModelsUpdate.get(i).setWheels(wheels);
                update=true;
            }
            if(update){
                subModelRepository.save(subModelsUpdate.get(i));
            }

        }
    }

    private void saveSubmodel(List<XmlSubModel> subModelList, Model model) {
        for(XmlSubModel xmlSubModel: subModelList){
            SubModel subModel = new SubModel();
            Engine engine = getEngine(xmlSubModel.getEngine());
            Wheels wheels = getWheels(xmlSubModel.getWheels());
            subModel.setLine(xmlSubModel.getLine());
            subModel.setModel(model);
            subModel.setEngine(engine);
            subModel.setName(xmlSubModel.getName());
            subModel.setYearTo(xmlSubModel.getTo()==null?model.getYearTo():xmlSubModel.getTo());
            subModel.setWheels(wheels);
            subModel.setYearFrom(xmlSubModel.getFrom()==null?model.getYearFrom():xmlSubModel.getFrom());
            subModelRepository.save(subModel);
        }
    }

    private Wheels getWheels(XmlWheels xmlWheels) {
        List<Wheels> wheelsList = wheelsRepository.findBySizeAndType(xmlWheels.getSize(), xmlWheels.getType());
        if(wheelsList.isEmpty()) {
            Wheels wheels = new Wheels();
            wheels.setSize(xmlWheels.getSize());
            wheels.setType(xmlWheels.getType());
            wheels = wheelsRepository.save(wheels);
            return wheels;
        }
        return wheelsList.get(0);
    }

    private Engine getEngine(XmlEngine xmlEngine) {
        List<Engine> engineList =  engineRepository.findByPowerAndType(xmlEngine.getPower(), xmlEngine.getType());
        if(engineList.isEmpty()){
            Engine engine = new Engine();
            engine.setType(xmlEngine.getType());
            engine.setPower(xmlEngine.getPower());
            engine = engineRepository.save(engine);
            return engine;
        }
        return engineList.get(0);
    }




    private void removeModelAndSubModel(Catalogue catalogue) {
        List<Model> modelList = modelRepository.findByCatalogue(catalogue);
        for (Model model:modelList){
            subModelRepository.deleteByModel(model);
        }
        modelRepository.deleteByCatalogue(catalogue);

    }

    public XmlCatalogue unmarshalCatalogue() throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlCatalogue.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = Paths.get("src\\main\\resources\\ford-example.xml")
                    .toFile();
            XmlCatalogue xmlCatalogue = (XmlCatalogue) unmarshaller.unmarshal(file);
            xmlCatalogue.setName(file.getName().split("\\.")[0]);
            //System.out.println(xmlCatalogue);
            return xmlCatalogue;
        } catch ( JAXBException ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }

    }
}
