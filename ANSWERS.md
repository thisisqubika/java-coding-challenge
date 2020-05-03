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

## E - Improvements