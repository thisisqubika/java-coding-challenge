# Challenge answers

## A - The entities

Below a short explanation about the decision taken regarding domain model design. 

![Domain Model](DomainModel.png)

### Brand

The service will support model specifications from several brands/factories and each of these brands will have a 
unique list of car specifications that they sell.  

Each `Brand` will contain a `name` and their list of `Specification`s. 

### Specification and Modification
Based on the car models structure and data provided into the sample file, I've identified a hierarchy of entities based 
on commons attributes as `name`, `Engine` and `Wheel`.  

An `Specification`s represent the base setup for a set of `Modifications`s that define small variants from the 
original spec.

> *NOTE : Some decisions were taken with the goal of design a normalized Domain Model and use some advance technics as
> using hierarchies and JPA, but if the domain model is so simple as the sample data, maybe a unique `Specification` 
>entity containing all the data is much simpler for maintenance.*   

### Engine

Based on the provided sample data the same `Engine` information could be used on several `Specifications`, so I've 
decided to create just one engine per _power:type_ tuple. This means that many `Specification` instances could be pointed to 
the same `Engine` if `power` and `type` attributes are equals.  

Also, I've modeled `EngineType` as an _Enum_. The pros are that this simplifies the string typos and keeps integrity in 
the domain model. The cons are that every time that a new engine type appears, a new version of the service must be 
deployed. An improvement is to transform this _Enum_ in a _Value Object_ list stored into the DataBase.

### Wheel

I've taken similar decisions for `Wheel` entities. They are unique id _size:type_ tuple is equaled.
In this case, I keep the type as a `String` 

### Ingestion
This entity is used to maintain a history of each `Brand`'s ingestion, registering the source, the ingestion date, 
and the `Specifications` processed and added during the process.  
This also allows checking the duplicated ingestion of the same source.

 
## B - Ingest the data
The ingestion process is designed and implemented to accomplish with the next definitions:

* Factories will only send catalogs for a single Brand
* Factories will send monthly files that should be ingested
* The same file should not be ingested twice but two different files from the same factory could contain the same
 car model
* If the model was not previously ingested, this will be created based on the data source
* If the model was previously ingested, will be replaced with the new model specification
* The same strategy will be applied to all the different brands and the data source provided 

![Class Diagram](ClassDiagram.png)

### Entities and Repositories - JPA

All the _Domain Model_'s entities were annotated using JPA and the corresponding _Repository_ was implemented to keep 
this complete model persistent.

### Brand Builder
_Factory Method_ that creates `Specification` for each car model providedfor a single `Brand`, parsing and adapting 
specific data sources (e.g. XML files, JSON files, web services, etc).  

A `FordBrandBuilder` was implemented to parse XML files provided from Ford brand and create the 
corresponding cars `Specification`.

A new `BrandBuilder` instance could be added into the `BuildersConfigurations` and the generated `Brand` will be 
ingested using the same `IngestStrategy` for all of them.

### Ingest Strategy
`IngestStrategy` implement the sstrategy to ingest new or existant Brands and Specifications.  

A `MergeBrandsIngestStrategy` was implemented to follow this definition:  
* If the model's `Specification` was not previously ingested, this will be created based on the data source  
* If the  model's `Specification` was previously ingested, will be replaced with the new model specification  

## C - Expose data with a RESTful API

I will take advantage of the `Spring Data Web Support` to expose all the Repositories implemented to accomplish the next requirements:

* Get a car specification by id
```CURL```
http://localhost:8080/specifications/{id}

* Get all the car specifications by brand
```CURL```
http://localhost:8080/specifications/search/findByBrandName?brand=Ford

## D - Adding images

## E - Improvements

* Spring Profiles (`dev` and `prod` required)
* Security
* 
