# Study System API

This project is a simple RESTful API for managing students, professors, classes, and subscriptions, built with Spring Boot and JPA.

## Prerequisites

- Java 17 or later
- Maven
- A running MySQL instance

## Getting Started

1.  **Clone the repository.**

2.  **Configure the database:**
    Update `src/main/resources/application.properties` with your MySQL credentials.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/StudySystem
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```
    The schema will be created automatically on startup (`spring.jpa.hibernate.ddl-auto=create`).

3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    The API will be available at `http://localhost:8080`.

## API Endpoints

### Students

- **POST /students**: Creates a new student.
  ```bash
  curl --location 'http://localhost:8080/students' \
  --header 'Content-Type: application/json' \
  --data '{ \
      "name": "Ada Lovelace", \
      "register": "18151129", \
      "email": "ada.lovelace@example.com" \
  }'
  ```

- **GET /students**: Retrieves a list of all students.
  ```bash
  curl --location 'http://localhost:8080/students'
  ```

- **GET /students/{id}**: Retrieves a single student by their ID.
  ```bash
  curl --location 'http://localhost:8080/students/1'
  ```

- **PUT /students/{id}**: Updates an existing student's details.
  ```bash
  curl --location --request PUT 'http://localhost:8080/students/1' \
  --header 'Content-Type: application/json' \
  --data '{ \
      "name": "Ada Lovelace King", \
      "register": "18151129-U", \
      "email": "ada.king@example.com" \
  }'
  ```

- **DELETE /students/{id}**: Deletes a student by their ID.
  ```bash
  curl --location --request DELETE 'http://localhost:8080/students/1'
  ```

### Professors

- **POST /professors**: Creates a new professor.
  ```bash
  curl --location 'http://localhost:8080/professors' \
  --header 'Content-Type: application/json' \
  --data '{ \
      "name": "Alan Turing" \
  }'
  ```

- **GET /professors**: Retrieves all professors.
  ```bash
  curl --location 'http://localhost:8080/professors'
  ```

- **GET /professors/{id}**: Retrieves a specific professor by their ID.
  ```bash
  curl --location 'http://localhost:8080/professors/1'
  ```

- **PUT /professors/{id}**: Updates an existing professor.
  ```bash
  curl --location --request PUT 'http://localhost:8080/professors/1' \
  --header 'Content-Type: application/json' \
  --data '{ \
      "name": "Dr. Alan Turing" \
  }'
  ```

- **DELETE /professors/{id}**: Deletes a professor by their ID.
  ```bash
  curl --location --request DELETE 'http://localhost:8080/professors/1'
  ```

- **GET /professors/{id}/history**: Retrieves the teaching history for a specific professor.
  ```bash
  curl --location 'http://localhost:8080/professors/1/history'
  ```

### Study Classes

- **POST /study-classes**: Creates a new class.
  ```bash
  curl --location 'http://localhost:8080/study-classes' \
  --header 'Content-Type: application/json' \
  --data '{ \
      "year": 2025, \
      "semester": 2, \
      "courseId": 1, \
      "professorId": 1 \
  }'
  ```

- **DELETE /study-classes/{id}**: Deletes a class by its ID.
  ```bash
  curl --location --request DELETE 'http://localhost:8080/study-classes/1'
  ```

### Subscriptions

- **POST /subscriptions**: Creates a new subscription for a student to a class.
  ```bash
  curl --location 'http://localhost:8080/subscriptions' \
  --header 'Content-Type: application/json' \
  --data '{ \
      "studentId": 1, \
      "studyClassId": 1 \
  }'
  ```

- **DELETE /subscriptions/{id}**: Deletes a subscription by its ID.
  ```bash
  curl --location --request DELETE 'http://localhost:8080/subscriptions/1'
  ```
