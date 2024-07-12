# studentapi Project README

This project provides a RESTful API for managing student and grade information. Student and grade details are stored in a PostgreSQL database. The API is developed using the Spring Boot framework and documented with Swagger.

## API Endpoints

### Student Operations

- **Create Student**

  `POST /student`

  Creates a new student.

- **Update Student by ID**

  `PUT /student/{id}`

  Updates a student by ID.

- **Update Student by Student Number**

  `PUT /student/studentNumber/{studentNumber}`

  Updates a student by student number.

### Grade Operations

- **Add Grade**

  `POST /grades`

  Adds a new grade.

- **Update Grade by Student Number**

  `PUT /grades/{studentNumber}`

  Updates a grade by student number.

## Technologies

- Java 14+
- Spring Boot
- PostgreSQL
- Swagger
- Lombok

## Project Structure

The project follows the MVC (Model-View-Controller) architecture and includes the following main components:

- **Controller**: Manages REST API requests.
- **Service**: Implements business logic and manages database operations.
- **Repository**: Provides JPA repositories for database interactions.
- **Entity**: POJO classes representing database tables.
- **DTO (Data Transfer Object)**: Data transfer classes used for API requests and responses.
- **Exception Handling**: Manages errors that occur during API requests.

## Getting Started

1. **Clone the Project**

   ```bash
   git clone https://github.com/cagri/studentapi.git


2. **Running the Project**

   ```bash
   ./mvnw spring-boot:run


- The application runs on 8080 port by default.

- You can access the Swagger UI interface by going to http://localhost:8080/swagger-ui/index.html from your browser.
