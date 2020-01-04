# Challenge answers

## A - The entities
The idea with this diagram is to separate each entity that I identified in the xml file. Therefore a car model is composed of two entities (wheel and engine), in addition a model has a list of submodels, which are also composed of two entities (wheel and engine).
## B - Ingest the data

## C - Expose data with a RESTful API

## D - Adding images
First of all, a byte array image field must be added to the entities that require it, using the @Lob and @Type annotations ("org.hibernate.type.BinaryType"). Then the application properties are modified and the following line is added (spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation = true). A column of type CLob must be created in the database

In the XML file add an element of type Base64.

In the Job you must create a processor to transform the base64 data to an array of bytes in java.

Then you must create a controller that handles requests related to uploading images or files and making the respective query.
## E - Improvements
This code can be improved by having the reading part of the xml file done in one step, that is, reading and mapping all the objects at the same time and writing to the database in the same step.
Additionally, the application is currently insecure, so it would be a good idea to implement a security layer by adding authentication and authorization. This is usually done by presenting a login screen.