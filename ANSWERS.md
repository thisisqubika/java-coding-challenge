# Challenge answers

## A - The entities

There will be 5 entities in the class diagram to represent the ford-example.xml. However
I added a 6th entity called CarBrand that will allow any company such as Ford, Chevy, Audi, BMW,
etc to post XML data to the REST service we will create. Here are the entities:

### 1. CarBrand 
CarBrand will contain the name of a vendor and the catalogue. CarBrand will have a One-to-One
mapping with Catalogue. CarBrand will allow us to search for a Model list based on brand name.

### 2. Catalogue
Catalogue will contain a list of Model. Catalogue will have a unidirectional One-to-Many relationship with Model

### 3. Model
Model will contain an Engine, Wheels, SubModel. Model will have a One-to-One relationship with Engine, Wheels and  SubModel.

### 4. Engine
Engine will contain 2 fields, power and type. Engine will have a One-to-One relationship with
Model.

### 5. SubModels
SubModel will contain a list of Models. SubModel will have a One-to-One relationship with the
Model that contains it. SubModel has a One-to-Many relationship with the Model list it contains.

### 6. Wheels
Wheels  will contain 2 fields, size and type. Wheels will have a One-to-One relationship with
Model.


## B - Ingest the data

#### 1. To read XML file from ingester I used the XStream library which I find very useful and easy to use to read
serialize XML strings to POJOs. I also created DTOs (Data Transfer Objects) to match every entity. The reason 
for introducing DTO is simple. We don't want to expose entities to our RestController, instead 
our RestController will only know about DTOs.
#### 2. List of DTOs:
CarBrandDto, CalalogueDto, ModelDto, SubModelDto, EngineDto and WheelsDto
Repositories were implemented to persist all entities. 
#### 3. The list of repositories are:
CarBrandRepository, CalalogueRepository, ModelRepository, EngineRepository, WheelsRepository
and SubModelRepository
#### 4. Two helper classed ConverterHelper and XStreamHelper were implemented. ConverterHelper to help with converting between entities and DTOs XStreamHelper to help with reading and writing XML.
#### 5. Added an exception CarBrandNotFoundException that will be thrown if the ingester finds that a brand already exists in the database. A brand name is unique and thus a check has to be done to make sure a row does not exist in the database.


## C - Expose data with a RESTful API

#### 1. Add a REST Controller CarBrandController to get model by ID and get models by brand
#### 2. Added and interface ICarBrandService and its implementation service CarBrandService.
#### 3. Added postman_get.model_by_id.png and postman_get.models_by_brand.png to display JSON response from
the 2 endpoints.
#### 4. Added a helper class JacksonHelper to pretty print JSON in the logs
#### 5. Endpoints exposed: GET /cars/{modelId} and GET /cars/byBrand/{name}


## D - Adding images

## E - Improvements