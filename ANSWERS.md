# Challenge answers

## A - The entities
When creating the relationships `Car` - `Wheels` and `Car` - `Engine`,
I created a many-to-one relationship assuming that a many models of cars can have the same model of wheels or engine.<br>
The concept of a car that can have sub-models, I modelled with a relationship one-to-many `Car` - `Car` bidirectional.
Also I configured that when deleting a parent model all the sub-models will be deleted.<br>
In the entity `Car` there are some attributes that can be null and I marked them with the annotation @Nullable. Those attributes are:
- line: because (at least for Ford) only sub-models define a line
- productionYearTo: when null indicates that a car is still in production
- parentModel: when null indicates that a car is the parent model and it can have sub-models

Note that in the UML diagram the `+` indicates a filed that has setter and getter public. I used `#` for `id` that has only getter public but setter protected.

## B - Ingest the data
The `FordIngesterTask` expects to find the files to ingest in a folder that is configurable with the property `cars.ford.ingester.root-path`.
If the folder doesn't exist, the application fails to start-up and an error will be logged describing that a folder must be created.<br>

When for any reason an error occurs during the ingestion, the file will be marked as failed to ingest, appending to the file name the extension `.failed`.
When the ingestion of a file is completed successfully, we append to the file name `.ingested` extension.

## C - Expose data with a RESTful API
The two REST endpoints exposed are:

- Get a car specification by id: method: `GET`, URL: `/cars/<id>` e.g. `/cars/10`
- Get all the car specifications by brand: method `GET`, URL: `/cars?brand=<brand>` e.g. `/cars?brand=ford`

In the first case if the specified car `id` parameter is not present in the DB, a `HTTP 404` error is returned.<br>
For the second endpoint the `brand` request parameter is optional and the search is case insensitive. If the URL `/cars` is called, all the cars will be returned.
This behaviour is on purpose to make the API more flexible.<br>

The car DTO returned contains all the fields from the car table except the parent model.
Also Engine and Wheels DTOs don't contain an ID because for now there is no endpoint that can be used with that ID. 

## D - Adding images

## E - Improvements