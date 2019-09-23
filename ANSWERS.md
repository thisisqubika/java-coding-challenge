# Challenge answers

## A - The entities
When creating the relationships `Car` - `Wheels` and `Car` - `Engine`,
I created a many-to-one relationship assuming that a many models of cars can have the same model of wheels or engine.<br>
The concept of a car that can have sub-models, I modelled with a relationship one-to-many `Car` - `Car` bidirectional.
Also I configured that when deleting a parent model all the sub-models will be deleted.<br>
In the entity `Car` there are some attributes that can be null and I marked them with the annotation @Nullable. Those attributes are:
- line: because (at least for Ford) only sub-models define a line
- productionYearTo: when null indicates that a car is still in production
- parentModel: when null indicates that a car is the parent model and it can have sub-models

Note that in the UML diagram the `+` indicates a filed that has setter and getter public. I used `#` for `id` that has only getter public but setter protected.

## B - Ingest the data
The `FordIngesterTask` expects to find the files to ingest in a folder that is configurable with the property `cars.ford.ingester.root-path`.
If the folder doesn't exist, the application fails to start-up and an error will be logged describing that a folder must be created.<br>

When for any reason an error occurs during the ingestion, the file will be marked as failed to ingest, appending to the file name the extension `.failed`.
When the ingestion of a file is completed successfully, we append to the file name `.ingested` extension.

## C - Expose data with a RESTful API
The two REST endpoints exposed are:

- Get a car specification by id: method: `GET`, URL: `/cars/<id>` e.g. `/cars/10`
- Get all the car specifications by brand: method `GET`, URL: `/cars?brand=<brand>` e.g. `/cars?brand=ford`

In the first case if the specified car `id` parameter is not present in the DB, a `HTTP 404` error is returned.<br>
For the second endpoint the `brand` request parameter is optional and the search is case insensitive. If the URL `/cars` is called, all the cars will be returned.
This behaviour is on purpose to make the API more flexible.<br>

The car DTO returned contains all the fields from the car table except the parent model.
Also Engine and Wheels DTOs don't contain an ID because for now there is no endpoint that can be used with that ID. 

## D - Adding images
To add images for each car model, we need to work on the `ingest` process (part `B`) and the `API` (part `C`).

Let's first analyse the `ingest` process. I would add a configurable `image store root path` of the folder that the ingest process will scan.
This is also our image store where all the images of the car will be saved. The root path will be something like `image-store/` and for the ingestion of each brand we scan a subfolder as: `image-store/ford`.
The client will need to copy the images in the proper configured folder. Having a single image store root path will help in case we need to migrate those data.<br>

The ingest process would be changed with an additional step after the un-marshalling of the XML car catalogue:
    for each car model we scan the image folder to see if there is an image file in there with the same name of the car model.
If so, we  we will save the image relative path into the DB (e.g. `ford/Aspire.jpg`) and leave the binary file in the filesystem.<br>

A preferable approach, if our customer can change the XML structure, would be to add the image file name in the catalogue.
The ingest process would look for an image specified in a new attribute `imageName` in the XML `MODEL` tag and it will save the image relative path into the DB.
This would solve the problem that a car model name can have some characters not allowed in a file name.<br>

For the REST API we can add a new endpoint that returns the image file: method `GET`, URL: `/cars/<id>/image`.
We can leverage Spring FileSystemResource in our Controller.

## E - Improvements