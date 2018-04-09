# pet-store

This pet-store is a 3-tier application for an online pet store. It provides the following features:

* users are able to see which pets are available for purchase
* staff can add new pets to the pet store’s inventory
* staff can remove pets that have been sold

## frontend: storefront

In the ``storefront`` folder you will find an Angular.js application implementing the front-end. It consumes the REST API exposed by the pet store backend services

This project was initialized with anglular-cli-1.7.3 by running ``ng new --routing  --skip-git  --style=scss storefront``

**technologies**: Angular-5.2.0, Angular Material-5.2.4 UI components, angular-cli-1.7.3, flex-layout (responsive), karma, protractor with jasmine, webpack will transpile typescript sources to ES5

Unit tests are run via ``karma`` and end-to-end functional tests are run via ``protractor``.

Typical commands:

* yarn install
* ng serve
* ng test
* ng test --code-coverage 
* ng e2e

## backend: pet-services

In the ``pet-services`` folder you will find a spring boot RESTful web service supporting the back-end and data tiers of the application.


This project was initialized by running ``./bin/springboot pet-services``

**technologies**: Spring Boot 2.0.1, Spring Actuator, Security, Data REST, Data JPA, MVC, Thymeleaf, Java 8, springfox, H2, hibernate

### build and test

To build the application and run the unit & integration tests, execute:
```
./mvnw clean install
```
After importing the project in your IDE, you can run the tests under ``src/test/java/com/fywss/spring/petservices``:

* unit tests are in ``PetTest.java``
* integration tests are in ``PetControllerIT.java`` and ``HomeControllerIT.java``

### run

To start the pet-services backend, execute:

```
./mvnw spring-boot:run
```

or

```
java -jar target/pet-services-1.0.0.jar
```

### try it out

Point your browser at ``http://localhost:9999/home`` to interact with the application.

Make sure to visit the spring boot actuator links at ``http://localhost:9999/actuatorlinks`` and the swagger API testing harness at ``http://localhost:9999/swagger-ui.html``


### automated build

Import the Jenkinsfile into a jenkins instance for an automated build pipeline.
Automated deploy of pet-services to Pivotal Cloud Foundry (PCF) is part of the build pipeline. Also, sonar analysis is pushed to SonarQube.

### todo

* authentication and authorization via Spring Security and Oauth
* automated deploy of storefront to PCF
* implement update functionality
* add more tests

