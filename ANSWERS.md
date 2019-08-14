# Challenge answers

## A - The entities
For this solution I created entity classes in com.mooveit.cars.domain package.
There are 4 classes (ModelTable, SubmodelTable, EngineTable and WheelsTable) that correspond to the tables created in the database to store the data from the xml file. Attached is the file "UML-domain-classes.png" with the class diagram for these classes and there relationships. ModelTable represents a car model with columns for id, name, brand, from, to, type, engine_id (foreign jey to engine table) and wheels_id (foreign key to wheels table). SubmodelTable represents a car submodel with columns id, name, line, from, to, engine_id (foreign jey to engine table), wheels_id (foreign key to wheels table) and model_id (foreign key to model table). EngineTable represents an engine entity with columns id, power and type. WheelsTable represents a wheels entity with columns id, size and type. A new engine and wheels entry is saved for each car model and submodel.

Also added package com.mooveit.cars.catalogue that contains classes to map the xml content and it structure. Attached the file "UML-catalogue-classes.png" with the class diagram for this package.

In the package com.mooveit.cars.dto I created classes to map the responses from the RESTful endpoints. Attached the file "UML-dto-classes.png" with the class diagram for this package. 

I created other packages to separate services, controllers and repositories. 
I created CarService and CatalogueService service interfaces and their implementations: CarServiceImpl (Service methods for RESTful API) and CatalogueServiceImpl (Service methods to read and store data from xml files into the) in the package com.mooveit.cars.service. In the package com.mooveit.cars.repositories I created some repository interfaces to handle the CRUD calls to the database and the package com.mooveit.cars.controllers contains the class CarsController to expose resources. Attached the file "UML-repository-classes.png" with the class diagram for these packages.

## B - Ingest the data
To get the data from the xml file, I harcoded the file name to get it from /resources/Catalogues/Ford.xml to get and process the provided file. (Will add some improvements in the improvements section).
The main reason for this change was because car models in ford-example.xml file don't have any attribute to set the brand. So a solution without modifing the xml structure would be to obtain the brand name from the xml file name. In this case, I get xml files from Catalogues folder naming it as {brand}.xml (From the example file "Ford.xml"). A better solution is to add the brand to the xml structure to avoid human errors when naming the xml files.
As for the database persistance, I stored the brand as a column in the model table. I did this change because the brand was not part of the data contained in ford-example.xml file.

## C - Expose data with a RESTful API

## D - Adding images

## E - Improvements