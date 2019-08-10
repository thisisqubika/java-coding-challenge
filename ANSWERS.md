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

Also, I've modeled `EngineType` as an _Enum_. The pros are that this simplifies the string typos and keeps integrity in the domain model. The cons are that every time that a new engine type appears, a new version of the service must be deployed. An improvement is to transform this _Enum_ in a _Value Object_ list stored into the DataBase.

### Wheel

I've taken similar decisions for `Wheel` entities. They are unique id _size:type_ tuple is equaled.
In this case, I keep the type as a `String`  
 
## B - Ingest the data

## C - Expose data with a RESTful API

## D - Adding images

## E - Improvements