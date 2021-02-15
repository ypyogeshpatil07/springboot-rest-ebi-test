#### Technologies / Frameworks Used
    * Spring Boot 2.1.0.RELEASE
    * Spring Web MVC
    * Spring Security (with simple in-memory authentication)
    * HTTP Basic Authentication
    * javax.validation
    * Spring Data JPA
    * Hibernate
    * H2 (In memory database) 
    * Swagger2
    * JUnit 5


#### Pre-requisites
* JDK 8 
* Maven 3.3 and above
* Docker Toolbox For windows/mac

#### Build
Clone the git repo to your local using `git clone https://github.com/ypyogeshpatil07/springboot-rest-ebi-test.git`.

Navigate to root folder of the cloned repo using cmd (windows) or terminal (MacOS) and issue `mvn clean install`.

This command will compile, test, and package a spring boot uber jar (with all dependencies).


#### Running the application
First build the application (refer to above section). Once the application has successfully built, 

* Open Docker Quickstart Terminal and Go to project root folder ,type command `docker build -t springboot-rest-ebi-test.jar .`
* Check docker image with a command `docker image ls`
* Run a docker image `docker run -p 9090:8080 springboot-rest-ebi-test.jar`
* To access application use IP address which is assigned by Docker when we open a tool box instead of localhost like [http://IP_ADDRESS:9090/person/]


#### REST Services
Swagger can be accessed using [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

A simple Authentication has been enabled with the following list of users.

| User / Password   | ROLES     |   Granted Authorities |
|-------------------|:---------:|:---------------------:|
| user / password   | USER      |    -                  |
| user2 / password  | USER      | READ_PRIVILEGES       |
| manager / password| MANAGER   | READ_PRIVILEGES,      | 
|                   |           | WRITE_PRIVILEGES      |

Authorities required for REST services:
> NOTE: id will be created incrementally from 1 to n..You can cross check your ids in H2(in-memory db) while
> application is up to access GET and update call.

| REST Service                  |   Authorities required    |
|:--------------------------    |:-------------------------:|
| **PUT** /update-person/{id}   | WRITE_PRIVILEGES          |
| **GET** /person-all           | READ_PRIVILEGES           |
| **POST** /store-person        | WRITE_PRIVILEGES          |
| **GET** /person-by-id/{id}    | READ_PRIVILEGES           |
| **DELETE** /delete-person/{id}| WRITE_PRIVILEGES          |

> NOTE: Please clear your browser cookies before using for any request and swagger-ui also otherwise 
> pop up for authentication may not come and also Swagger may use previous authentication details for subsequent calls.

Alternatively, Any REST client can be used without depending on Swagger UI.
