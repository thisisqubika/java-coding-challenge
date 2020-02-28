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

## D - Adding images

## E - Improvements