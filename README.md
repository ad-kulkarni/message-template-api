# About
This project is a RESTful API application built on Java SpringBoot that allows users to perform operations on message templates.
Following operations are supported by the API,
- Create message template
- Get all message templates
- Get message template by id
- Get message template by query params

# Technologies
- Java 8
- SpringBoot 2.2+
- Swagger UI 2.7
- H2 database

# Build tools
- Gradle 4.10 (Gradle 4+ recommended)

# Building/Running the project using Gradle
  After cloning the project and installing Gradle, use a command line tool and navigate to the root directory of the project.
  Use the following command to run the application,<br/><br/>
  ``` gradle bootRun ```
  <br/><br/>
  Once the application is running successfully, navigate to Swagger UI - http://localhost:8080/swagger-ui.html#
  or use any REST client of your choice like Advanced REST Client (https://install.advancedrestclient.com/install)
  <br/>

# Note
  Don't recommend using Swagger UI for urls with query params because of an existing issue with the springfox library (https://github.com/springfox/springfox/issues/2338) and seems like it is still unresolved.
