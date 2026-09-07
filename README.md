# ND035-P02-VehiclesAPI-Project
## Source
This project originated from Udacity's [Java Web Developer course](https://www.udacity.com/enrollment/nd035) and was the final project of chapter 3 (Web Services and APIs). In the project repository, a Vehicles API using Java and Spring Boot that can communicate with separate location and pricing services was implemented. The provided code can be found [here]([https://github.com/udacity/nd035-c1-spring-boot-basics-project-starter/tree/master/starter/cloudstorage](https://github.com/udacity/nd035-C2-Web-Services-and-APIs-Exercises-and-Project-Starter/tree/master/P02-VehiclesAPI)). 
The task description of the code owner is located in the README files inside the subfolders.
- [Vehicles API](vehicles-api/README.md)
- [Pricing Service](pricing-service/README.md)
- [Boogle Maps](boogle-maps/README.md)

## Competences
In this project I have applied the competences taught in this chapter:
- **REST APIs**: endpoints, HTTP methods, request/response flow, and how backend services expose data
- **GraphQL APIs**: how clients can ask for exactly the data they need instead of fixed REST responses
- **Microservices**: splitting systems into smaller services and concepts like service communication/discovery
- **Security**: protecting APIs and controlling access
- **Consuming external services**: calling SOAP and REST APIs from your own Java app
- **Documentation + testing**: documenting APIs and writing unit/integration tests for them

## Instructions
Note that **all four applications** -- eureka server, boogle-maps, pricing-service and vehicles-api -- **should be running at once** for full operation. The applications can be launched in any order and should start without any errors.

## Documentation of results
### Convert the Pricing Service
As requested by the project description the Pricing Service was converted to a microservice. In order to test the functionality two additional tests were implemented outside the `contextLoads()` test to test the functionality for the overall service. Those test the response to valid and invalid price requests. \
![](/images/Screenshot+2026-09-04+155158.png)

### Implement the Vehicles API
#### CREATE operations
The Vehicles API is able to create a new vehicle based on input from the user with a POST request.
Manual request:: \
![](/images/Screenshot+2026-09-07+103409.png)

In the swagger documentation: \
![](/images/Screenshot+2026-09-07+085053.png)
![](/images/Screenshot+2026-09-07+085213.png)

#### READ operations
The Vehicles API can receive GET requests from a user, and read back either a list of all existing vehicles, or the data for a single vehicle. \
Manual request:: \
![](/images/Screenshot+2026-09-04+155339.png)
![](/images/Screenshot+2026-09-07+101052.png)
![](/images/Screenshot+2026-09-04+155544.png)

In the swagger documentation: \
![](/images/Screenshot+2026-09-04+161206.png)
![](/images/Screenshot+2026-09-07+085246.png)
![](/images/Screenshot+2026-09-07+085555.png)

#### UPDATE operations
The Vehicles API can update an existing vehicle through input from the user.
Manual request:: \
![](/images/Screenshot+2026-09-04+160243.png)
![](/images/Screenshot+2026-09-07+110929.png)

In the swagger documentation: \
![](/images/Screenshot+2026-09-07+102626.png)
![](/images/Screenshot+2026-09-07+102651.png)
![](/images/Screenshot+2026-09-07+085628.png)
![](/images/Screenshot+2026-09-07+085634.png)

#### DELETE operations
The Vehicles API can delete an existing vehicle when requested by the user.
Manual request: \
![](/images/Screenshot+2026-09-04+155622.png)
![](/images/Screenshot+2026-09-07+104327.png)

In the swagger documentation: \
![](/images/Screenshot+2026-09-07+085720.png)
![](/images/Screenshot+2026-09-07+085735.png)

#### Consume data from external services
The Vehicles API is able to consume information from the separate Boogle Maps and Pricing Service APIs, and return that information as part of the vehicle information for a single vehicle.
Manually sending a POST request: \
![](/images/Screenshot+2026-09-04+155339.png)

In the swagger documentation: \
![](/images/Screenshot+2026-09-04+161206.png)

Note: Boogle Maps will assign a new random address each time a query is called, so the changes between queries are expected.

### Testing the Vehicles API
Tests are implemented for the Vehicles API CarController that cover the CRUD (Create, Read, Update, Delete) operations.
![](/images/Screenshot+2026-09-04+154658.png)

### Eureka Server
Both the Pricing Service and the Vehicles API were exposed to the Eureka server. The latter was also exposed despite not required by the project description as otherwise the communication would _technically_ not be through the eureka server and still through REST. Doing so, enables us to call the price service endpoint in a very neat way: `http://PRICING-SERVICE`.
![](/images/Screenshot+2026-09-04+160436.png)
The eureka server information is available [here](http://localhost:8761/)

### API Documentation
![](/images/Screenshot+2026-09-04+160731.png)
The project was documented by using the swagger framework, which is available [here](http://localhost:8080/swagger-ui.html).

