# Challenge answers

## A - The entities
UMLmodel.png was added to the project , so there you will find the structure that i use.

A comment that i need to do in this part is:
As you can see i use Enumerations for Engine Type, Model Line, Model Type and Wheel Type, i know that i can use tables for this entities 
but in some cases is better use enumerations.

## B - Ingest the data

As you can see there are five repositories, one of them is FileProcessedRepository i use that because in a real case i need to persist the 
files that are already processed.

## C - Expose data with a RESTful API

Postman Collection 
https://www.getpostman.com/collections/97c9b5e3840d39497e1d
In this postman Collection you can find the methods.
(
    -   Get a car specification by id
    -   Get all the car specifications by brand
)

In this point i use RestRepositories (https://spring.io/projects/spring-data-rest), the main reason is because this allow me to avoid the boilerplating using the structure Controller->Service->ServiceImplementation->Repository, you can use this kind of structure when you need to perform some actions on the service layer, maybe data transforming or another bussines logic things, but if you only need to expose some info RestRepositories is perfect.

## D - Adding images

If i need to implement this, i will use something like base64 to hold the image in the database, maybe something like this 

CREATE TABLE models (
    id  IDENTITY    PRIMARY KEY,
    thumbnail   VARCHAR DEFAULT NULL,
    /*other fields*/
)

CRETE TABLE model_images (
    id_model INT,
    id_image INT,
    PRIMARY KEY (id_model, id_image);
)

CRETE TABLE images () {
    id  IDENTITY    PRIMARY KEY,
    large_image    VARCHAR NOT NULL,
    medium_image    VARCHAR DEFAULT NULL,
    small_image     VARCHAR DEFAULT NULL
}

this allow the FrontEnd developer consume the (/models) REST service and get a very small image (thumbnail) with every model stored in the DB, and when the FrontEnd developer need a very specific model with all the images related can put in a DTO all the images related in the table (model_images)

## E - Improvements
- Implement a generic logic to process files for diferent brands avoiding the fact that every brand can have its own attibutes, i mean in this moment i use the Ford file , but maybe the client needs to process the Chevrolet, Audi, Mercedes, etc.. files.
- Use a structure like Controller, Service, Service Implementation, Repository, in case that we need to do some bussines logic with the data that will be consumed via REST. for example: add information to the models in a DTO that contains the name of the file from wich the model was created.



