# Challenge answers

## A - The entities
- I modify pom to add liquibase (https://www.liquibase.org/) dependency and the uml model image (ford-uml.png).

## B - Ingest the data
- I add a property to load a couple of brands, besides of create the need it services to process the DTOs 
based on the XML file.
- Add the util class JacksonXmlDataConvert to convert XML file data to DTOs

## C - Expose data with a RESTful API
- Added Jackson annotation to support bidirectional relationships and get the entity as a json object

## D - Adding images
*Option 1: To manage the files in database, first will add a column with type CLOB or BLOB in the SubModel table,
and assign a filename according to a pattern given by the SubModel, like name-line, and limit the type of file extension
(.jpg, .png, .jpeg, etc), and add a few properties for restriction as limit the size of file.

*Option 2: Add a column in SubModel table to refer a URL where the file is saved in a storage in cloud
like Amazon Simple Storage Service (Amazon S3).   

## E - Improvements
- Add some validation for value column as Model.Type where the values are constant.
- Add other REST services to have CRUDs of the left entities.
- Add Swagger API for document the REST services.
- Add a few Views as Welcome Page and other to manage information.
- Add log configuration to manage the different types to see in console (DEBUG,TRACE, etc).
- Add profile configuration to handle a Development and Production version for compilation.
- Add SonarQube configuration to ensure code quality.