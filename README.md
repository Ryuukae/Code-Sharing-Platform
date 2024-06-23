
# Code Sharing Platform

## About

A graduate project sourced from Hyperskill, this program serves to act as a closed local network code "snippet" sharing platform, similar to that of pastebin.

## Overview

The Code Sharing Platform is a Java application built using the Spring Boot framework designed to enable users to share and view code snippets. It offers a RESTful API and a web interface for users to interact with the service.

## Functionality

The platform provides the following features:

- REST API endpoints for submitting and retrieving code snippets in JSON format.
- A web interface for viewing code snippets in HTML format.
- Exception handling for consistent error responses across the application.
- A service layer that contains the business logic for code snippet management.
- An embedded static code snippet for demonstration purposes.

## Project Structure

The project is structured into the following packages:

- `src/platform/controller`: Contains controllers for handling HTTP requests.
  - `ApiController.java`: Handles API requests and returns JSON responses.
  - `WebController.java`: Handles web interface requests and returns HTML responses.
- `src/platform/errorhandling`: Contains the global exception handler for the application.
  - `GlobalExceptionHandler.java`: Manages exceptions across controllers.
- `src/platform/model`: Contains the data models for the application.
  - `CodeSnippet.java`: Represents the structure of a code snippet.
- `src/platform/service`: Contains the service logic for code snippet operations.
  - `CodeSnippetService.java`: Manages retrieval and storage of code snippets.
- `src/resources`: Holds the application's configuration properties.
- `test`: Contains tests for validating the application's functionality.

## Configuration

Configuration settings are located in the `application.properties` file, which includes:

- Server port configuration.
- Logging level and file output settings.
- Thymeleaf template locations.

## Setup

To set up the platform, clone the repository and import it as a Maven project into an IDE. Ensure Java and Maven are installed on the system.

Start the application with:

```shell
mvn spring-boot:run
```

## Endpoints

The application exposes the following endpoints:

- `POST /api/code/new`: Accepts a new code snippet and returns its ID.
- `GET /api/code/{id}`: Retrieves a code snippet by its ID in JSON format.
- `GET /api/code/latest`: Returns the latest code snippets in JSON format.
- `GET /code/new`: Displays a form for submitting a new code snippet.
- `GET /code/{id}`: Displays a code snippet by its ID in HTML format.
- `GET /code/latest`: Shows the latest code snippets in HTML format.

## Restrictions

### View Restriction
The view restriction feature limits the number of times a code snippet can be viewed. Once the snippet has been viewed a certain number of times, it becomes inaccessible. 
This is typically implemented by storing a counter with each snippet that tracks the number of views. Each time the snippet is accessed, the counter is decremented. When the counter reaches zero, the snippet is no longer available.

### Time Restriction
The time restriction feature limits the duration that a code snippet is available after it has been posted. This is usually implemented by storing a timestamp with each snippet that represents the time at which the snippet will expire. 
Each time the snippet is requested, the current time is compared to the expiration timestamp. If the current time is past the expiration time, the snippet is considered expired and is no longer available.

## Contribution

Contributors can fork the repository, make changes, and submit a pull request for review.

## License

The project is under the MIT License, as found in the LICENSE file.

For more information, please refer to the source code and comments within the files.
