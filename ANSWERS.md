# Challenge answers

## A - The entities

Planning to parse the xml data in to four tables. 

MODEL 
SUBMODEL
WHEELS
ENGINE

Plan is to use the same MODEL table for all Model xml elements. 
The differentiation between models under submodels and a regular model, will be based on the parent_sub_model_id column in MODEL table.
If the parent_sub_model_id column is blank or null then it is a regular model, if it has a value, then it is a model under submodels.

Each Model table row will link to other three tables by it's Primary key model_id. Except for the submodels-model rows.

For submodels-model rows there will be no link/dependency to Submodel table. (as there are no furher submodels under these models)

This data design is to use minimum set of tables, by using a data dependency(parent_sub_model_id column).






## B - Ingest the data

Added the service, which will be called from the task.





## C - Expose data with a RESTful API

Added API's in controller for the below

http://localhost:8080/saveCar
Test call to save hard coded to database, to check the tables are loaded fine.

http://localhost:8080/processCars
Call to ingest the xml data to database

http://localhost:8080/getCarById?id=1
Get car by model id url

http://localhost:8080/getCarByBrand?brand=Aspire
Get car by brand name url





## D - Adding images

Different ways to do it for different scenarios

#1 Save the image as BLOB in DB and send the same in response and convert it to Base64 to display.

#2 Store the images in a filesystem and its path/uri in the database, send the uri links in json response if they can be directly accessed.

#3 Send the response as Multipart HTTP respons, and add the images in the multi-part response as attachments to the caller.



## E - Improvements

If needed, we can add an additional table for submodels-model, 
so the differentiation between model and submodels-model will be identified based on table rather than on data.
Not implemented..
