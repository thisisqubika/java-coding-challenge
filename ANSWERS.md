# Challenge answers

## A - The entities

## B - Ingest the data

Domains created with respective services and repositories.
File reader created using JAXB.
Error handling for file parsing with custom exception.
Models created for parsing files.
Basic tests for entities creation.

## C - Expose data with a RESTful API

Endpoint to search model by name. 		/car/model/name/{name}
Endpoint to search model by id. 		/car/model/name/{id}
Controller advise with exception handler for not existing models.
Custom JPA methods on repositories.
Basic tests for getting models.


## D - Adding images

I have different approaches to this point:

1) If the image url comes in the XML file I would just save it in a new column, and get it from the db when the user searches the respective model.

2) I would create an endpoint in where the user sends model param and a multipart file to attach to that model. 
Create this image on local server from bytes or save it on the db as a binary large object.
When user searches by model I would return it back as an byte array.

3) I would create endpoint to receive an image and upload it to a host or a cloud and persist the link.


## E - Improvements
