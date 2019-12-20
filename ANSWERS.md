# Challenge answers

## A - The entities 
The class diagram is in the resources folder of the project
A Car class is created to simplify the code and implement this class in CarModel and CarSubModel
## B - Ingest the data

## C - Expose data with a RESTful API
it is necessary to add a brand field, in order to specify this property to each vehicle, the brand field is null so as not to affect the tests with the original xml file, but this field must be not null
the service is exposed to consult all the vehicles and their characteristics.
The services are exposed to consult the vehicles by ID and by brand
The service to create vehicles with an XML file is exposed, in case a customer wishes to create their vehicles
## D - Adding images
a clob type field must be included in the database, in the xml file an element called image that will contain the image in base 64 must be added, in the java classes, properties must be created in the domain Car classes
## E - Improvements
As it improves I would skip the CRON and use the service of creating vehicles for greater control of the data
As an improvement I would propose to generate a JSON instead of an XML for data processing