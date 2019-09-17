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

## C - Expose data with a RESTful API

## D - Adding images

## E - Improvements