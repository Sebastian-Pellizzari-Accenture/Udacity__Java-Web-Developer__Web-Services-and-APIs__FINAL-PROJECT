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
#### READ operations
The Vehicles API can receive GET requests from a user, and read back either a list of all existing vehicles, or the data for a single vehicle. \
Manually sending a POST request: \
![](/images/Screenshot+2026-09-04+155339.png)
![](/images/Screenshot+2026-09-07+084924.png)

In the swagger documentation: \
![](/images/Screenshot+2026-09-04+161206.png)
![](/images/Screenshot+2026-09-07+085246.png)

#### Consume data from external services
The Vehicles API is able to consume information from the separate Boogle Maps and Pricing Service APIs, and return that information as part of the vehicle information for a single vehicle.
Manually sending a POST request: \
![](/images/Screenshot+2026-09-04+155339.png)

In the swagger documentation: \
![](/images/Screenshot+2026-09-04+161206.png)

Note: Boogle Maps will assign a new random address each time a query is called, so the changes between queries are expected.
