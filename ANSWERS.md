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

## C - Expose data with a RESTful API

## D - Adding images

## E - Improvements