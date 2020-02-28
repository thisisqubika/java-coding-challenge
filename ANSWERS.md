# Challenge answers

## A - The entities
I do not use Enum for types and lines, because I think we can get new lines or types from a new xml file.
I could use a Enum for size in Wheels, but I need to know all values in the market.
If I had used Enums, I will need to compile the project to add a new value for any Enum.
## B - Ingest the data
When the system read an archive, the system replace all the old models and submodels for the new ones. Because this is the new catalogue.
The system doesn't erase engines neither wheels because they have standard values and several models and submodels can to use the same values, the system expects several catalogue files not only ford.
In each submodel when the submodel doesn't have "form" or "to", the system takes those values from the principal model.
## C - Expose data with a RESTful API
The system exposes the specification from models and submodels. All of them are valid specifications
## D - Adding images
For upload images the system can use multipart form spring, and save them as files or blobs in DB, and for download the system can to serve them as file or as byte array
## E - Improvements
The system can give the option to change the type of Wheels with the same size.
The system is ready for large xml files, and now the system look for models and submodels and create the new ones in the xml file and delete the old ones in the bd, and update if they come in the xml file and exist in the DB and if the have any change, and It is posible to improve if save the last sha1 for brand xml and compare with the next and decide if process or not process the file.
the system need more methods in the api.