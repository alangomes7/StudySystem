# studentJPA

This project is a simple RESTful API for managing students, built with Spring Boot and JPA.

## Prerequisites

- Java 17 or later
- Maven
- A running MySQL instance

## Getting Started

1.  **Clone the repository.**

2.  **Configure the database:**
    Update `src/main/resources/application.properties` with your MySQL credentials.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/StudentApi
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

The following examples, derived from `service_request.txt`, demonstrate how to use the API.

### 1. Add a new Student
- **Method:** `POST`
- **URL:** `/students`
- **Description:** Creates a new student.
- **Expected Response:** `201 Created` with the student object including an ID.
- **Example:**
  ```bash
  curl --location 'http://localhost:8080/students' \
  --header 'Content-Type: application/json' \
  --data '{
      "name": "Ada Lovelace",
      "register": "18151129",
      "email": "ada.lovelace@example.com"
  }'
  ```

### 2. Get All Students
- **Method:** `GET`
- **URL:** `/students`
- **Description:** Retrieves a list of all students.
- **Expected Response:** `200 OK` with a JSON array of all students.
- **Example:**
  ```bash
  curl --location 'http://localhost:8080/students'
  ```

### 3. Get a Specific Student by ID
- **Method:** `GET`
- **URL:** `/students/{id}`
- **Description:** Retrieves a single student by their ID.
- **Expected Response:** `200 OK` with the student object, or `404 Not Found` if the ID does not exist.
- **Example (Success):**
  ```bash
  # Replace '1' with an actual student ID
  curl --location 'http://localhost:8080/students/1'
  ```
- **Example (Not Found):**
  ```bash
  curl --location 'http://localhost:8080/students/999'
  ```

### 4. Modify an Existing Student
- **Method:** `PUT`
- **URL:** `/students/{id}`
- **Description:** Updates an existing student's details.
- **Expected Response:** `200 OK` with the updated student object.
- **Example:**
  ```bash
  # Replace '1' with the ID of the student to update
  curl --location --request PUT 'http://localhost:8080/students/1' \
  --header 'Content-Type: application/json' \
  --data '{
      "name": "Ada Lovelace King",
      "register": "18151129-U",
      "email": "ada.king@example.com"
  }'
  ```

### 5. Remove a Student
- **Method:** `DELETE`
- **URL:** `/students/{id}`
- **Description:** Deletes a student by their ID.
- **Expected Response:** `204 No Content`.
- **Example:**
  ```bash
  # Replace '1' with the ID of the student to delete
  curl --location --request DELETE 'http://localhost:8080/students/1'
  ```