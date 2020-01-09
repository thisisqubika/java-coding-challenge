# Challenge answers

## A - The entities

 I decided to represent the Models and Submodels in the same entity (CAR_MODEL), since
 they are essentially the same object but are used to represent different levels of cars.
 Likewise, this model would give us vertical scalability of the data, if there is a new 
 level for cars in the future.

## B - Ingest the data

 The data ingest algorithm was implemented  with recursive approach, since the data model 
 allows adding new levels of cars. Adding a new level of cars in the input file, would not 
 be a limitation for the application.

## C - Expose data with a RESTful API

 Notes: HATEOAS was used for hyperlinks and pagination of Rest Services.

## D - Adding images

 In case of having image files that refer to the data of the input file ford-input.xml, we can add
 one more attribute to the Model object and this attribute would be a key of the image, this can be
 a ulrpath or a path of The image on a server. In this same order of ideas, when exposing the object, with
 HATEOAS we can show a hyperlink in the urlpath attribute (created in the previous item). In addition, we
 will have an endpoint with a single function of exposing the image of a Model in the expected format, which
 would be referenced in the hyperlink of the urlpath attribute.

## E - Improvements

 As improvements to data loading, it is a good option to use spring-integration with inbound-channels
 and service-activator. it can be define a flow with channels where the first channel would be an inbound-channel
 to read the files and the rest of the channels, could be service-activater channels to process the files, as well
 as a hospital channel to store the files that for some reason failed while they were processed.
 
 Api documentation: at the time of this development there is an issue (https://github.com/springfox/springfox/issues/2932) 
 for springfox-swagger. since there are problems running when you use swagger 2.9.2 with spring boot 2.2.0 and HATEOAS. 
 In the next versions of swagger this could be solved. Another alternative is Open Api (https://github.com/springdoc/springdoc-openapi).
 
 The application has a set of unit tests with JUnit5, Mockito and integration tests with Spring Test, 
 however the tests are never left over, so more tests can be added to the application, to achieve greater code coverage.