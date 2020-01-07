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

To support image addition I would add a new table that contains the image id and a blob representing the image, which will be 
stored as a String encoded as Base64 (lets suppose we call the table IMAGE).

I would also add to model and submodel's tables, a new column "image_id", that contains the image related to the model, and 
will be represented in the entity as a String, which for convenience sake I'll @JsonInclude(Include.NON_NULL)
so in the response of the API call it wont show if there isn't an image associated to the mnodel. I'll expose the image as a
String encoded in Base64 in the response. That's if every model only has one image associated. 

If the models/submodels can have more than one image associated,my approach would be different, having IMAGE a total of 4 
columns: The id, the blob that contains the image, a column "model_id", and a column "submodel_id". 
Both of the last two columns will be nullable (as the image can be associated to a model, or a submodel, or both a model and
a submodel).

The entities for MODEL and SUBMODEL will have to change to accomodate for this, having now a new List<String> representing 
the images related to given model/submodel (this will be a OneToMany association).

When sending the reponse, I'll do the same approach as with one image, choosing to send the images as a list of encoded Base64 
Strings.

A perhaps better solution would be to have the images not send as a String on the response for the endpoints created,
but create a new endpoint "/car/image/{id}", which returns the image solicited by id as an "image/png".

And how do we get the id of the image?
Well we have a couple of ways to do it, one would be having another endpoint "/car/{id}/images" that returns a list of id
of the images associated to that car (this will envolve the creation of new queries in the repositories), and another approach
would be directly giving the link to the different endpoints with the images, for each model and submodel, in the same 
endpoints created for item C. 

## E - Improvements

One of the things that will improve my solution would be the addition of a security layer, having to authenticate to call the 
endpoints, or if it will only be consumed by our local branches, we can secure it using Client Certificates.

Another improvement would be to add a cache for the services.

Also I would change the use of a brand column in Model, for a "brand_id" column, which references a new table "Brand", 
containing the brands supported by our Customer.

For documentation, OPEN API/Swagger could be used.