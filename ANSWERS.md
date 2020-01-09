# Challenge answers

## A - The entities

 I decided to represent the Models and Submodels in the same entity (CAR_MODEL), since
 they are essentially the same object but are used to represent different levels of cars.
 Likewise, this model would give us vertical scalability of the data, if there is a new 
 level for cars in the future.

## B - Ingest the data

 The data ingest algorithm was implemented  with recursive approach, since the data model 
 allows adding new levels of cars. Adding a new level of cars in the input file, would not 
 be a limitation for the application.

## C - Expose data with a RESTful API

## D - Adding images

## E - Improvements