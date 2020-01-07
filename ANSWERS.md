# Challenge answers

## A - The entities

I decided to model this with four entities (and four tables).
An Engine entity for ENGINE nodes in the XML file, that contains information about the Car's engines. It has power and type.
A Wheel entity for WHEELS nodes in the XML file, that contains information about the car's wheels. 
A  SubModel entity for SUBMODELS nodes in the XML file, that contains information about different submodels for a car's model, 
and an entity called Model for the MODEL nodes in the XML file, that contains all the information a car's Model can have.

For more information about entities and database tables, take a look at the UML and ERM diagrams added.

## B - Ingest the data

I've created the domain based on the design in item A. For each entity I've created a repository based on JpaRepository and a service to interact with. I've created a couple of custom queries in the repository using query creation from the method's name. 

I also had to create custom logic on the service for each repository in order to avoid data duplication. For this, I assumed that a model and a submodel's name are unique, and that two engines with the same power and the same type are the same engine (same goes for a wheel, two wheels of the same size and type are the same wheel).

A file reader was added that reads different files for different brand's car specifications, and returns CarSpecifications (which is a superclass that all different formats
for different brands should implement). In the particular case of Ford's specifications, this will be casted as a Catalogue, and will be processed by a FordIngesterService, 
which will process the Catalogue and persist it into the database. My design accounts for updates to the Model and Submodels (be it a year that the brand ended production of 
that particular model, or addition of submodels for the model, which wheels or engine it uses, etc), except for changes on the Model and SubModel's name. This property is supposed to be the same always.

To ingest the data I created a super class "IngesterTask" that manages the ingestion of data for different brands, with sub classes that calls the ingestion method with 
different ingester services created specifically for each brand. 

## C - Expose data with a RESTful API

I created a CarController for all car information related endponts.
As a part of this I created custom response entities for the endpoints to return. 
Also, I added ControllerAdvice to handle exceptions and not return the error to the user.
Tests for CarController were added.

## D - Adding images

## E - Improvements
