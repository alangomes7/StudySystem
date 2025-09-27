# Student Study System API

Welcome to the Student Study System API. This is a RESTful API built with Java and Spring Boot that manages students, professors, courses, and their relationships within a study system.

## Table of Contents

- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [API Endpoints](#api-endpoints)
  - [Students (`/students`)](#students-students)
  - [Professors (`/professors`)](#professors-professors)
  - [Courses (`/courses`)](#courses-courses)
  - [Study Classes (`/study-classes`)](#study-classes-study-classes)
  - [Subscriptions (`/subscriptions`)](#subscriptions-subscriptions)


---

## Prerequisites

- Java 21 or later
- Apache Maven
- An IDE like IntelliJ IDEA or VS Code (recommended)

---

## How to Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/alangomes7/StudySystem
    cd StudySystem
    ```

2.  **Run the application using Maven:**
    ```bash
    mvn spring-boot:run
    ```

The API will be available at `http://localhost:8080`.

---

## API Endpoints

Below are the available endpoints with `curl` examples.

### Students (`/students`)

Manages student records.

**1. Get All Students**
```bash
curl --location 'http://localhost:8080/students'
```

**2. Get a Student by ID**
```bash
curl --location 'http://localhost:8080/students/1'
```

**3. Create a New Student**
```bash
curl --location 'http://localhost:8080/students' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Jane Doe",
    "phone": "+5599999999999",
    "email": "jane.doe@example.com",
    "register": "S12345"
}'
```

**4. Update a Student**
```bash
curl --location --request PUT 'http://localhost:8080/students/1' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Jane Doe",
    "phone": "+5599999999999",
    "email": "jane.doeUpdated@example.com",
    "register": "S12345"
}'
```

**5. Remove a Student**
```bash
curl --location --request DELETE 'http://localhost:8080/students/1'
```

---

### Professors (`/professors`)

Manages professor records.

**1. Get All Professors**
```bash
curl --location 'http://localhost:8080/professors'
```

**2. Get a Professor by ID**
```bash
curl --location 'http://localhost:8080/professors/1'
```

**3. Create a New Professor**
```bash
curl --location 'http://localhost:8080/professors' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Professor Mark Smith",
    "phone": "+5599999999999",
    "email": "mark.smith@example.com",
    "register": "P12345"
}'
```

**4. Update a Professor**
```bash
curl --location --request PUT 'http://localhost:8080/professors/1' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Dr. Alan M. Turing",
    "phone": "+5599999999999",
    "email": "mark.smith@example.com",
    "register": "P12345"
}'
```

**5. Delete a Professor**
```bash
curl --location --request DELETE 'http://localhost:8080/professors/1'
```

---

### Courses (`/courses`)

Manages course information (e.g., "Computer Science 101").

**1. Get All Courses**
```bash
curl --location 'http://localhost:8080/courses'
```

**2. Get a Course by ID**
```bash
curl --location 'http://localhost:8080/courses/1'
```

**3. Create a New Course**
```bash
curl --location 'http://localhost:8080/courses' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Introduction to Algorithms",
    "description": "Programing logic"
}'
```

**4. Update a Course**
```bash
curl --location --request PUT 'http://localhost:8080/courses/1' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Advanced Algorithms",
    "description": "Advanced programing logic"
}'
```

**5. Delete a Course**
```bash
curl --location --request DELETE 'http://localhost:8080/courses/1'
```

---

### Study Classes (`/study-classes`)

Manages specific offerings of a `Course` taught by a `Professor` in a given year/semester. Each class is automatically assigned a unique `classCode` upon creation (e.g., `IA20242`).

**1. Get All Classes (or Filter by Professor)**
```bash
# Get all classes
curl --location 'http://localhost:8080/study-classes'

# Get all classes taught by professor with ID 1
curl --location 'http://localhost:8080/study-classes?professorId=1'
```

**2. Get a Class by ID**
```bash
curl --location 'http://localhost:8080/study-classes/1'
```

**3. Create a New Class**
*(Assumes a course with ID 1 and a professor with ID 1 exist. Professor is optional)*
```bash
curl --location 'http://localhost:8080/study-classes' \
--header 'Content-Type: application/json' \
--data '{
    "year": 2024,
    "semester": 2,
    "courseId": 1,
    "professorId": 1
}'
```

**4. Assign/Change Professor for a Class**
```bash
curl --location --request PUT 'http://localhost:8080/study-classes/1/professor' \
--header 'Content-Type: application/json' \
--data '{
    "professorId": 1
}'
```

**5. Delete a Class**
```bash
curl --location --request DELETE 'http://localhost:8080/study-classes/1'
```

---

### Subscriptions (`/subscriptions`)

A Subscription links a `Student` to a `StudyClass`.

**1. Get Subscriptions (with optional filters)**
*   `GET /subscriptions`: Returns all subscriptions.
*   `GET /subscriptions?studentId=1`: Returns a student's subscription history.
*   `GET /subscriptions?studyClassId=1`: Returns the roster of students for a class.

```bash

# Get history for student with ID 1
curl --location 'http://localhost:8080/subscriptions?studentId=1'

# Get all students in class with ID 1
curl --location 'http://localhost:8080/subscriptions?studyClassId=1'
```

**2. Create a new Subscription (Enroll a Student)**
*(Assumes a student with ID 1 and a study class with ID 1 exist)*
```bash
curl --location 'http://localhost:8080/subscriptions' \
--header 'Content-Type: application/json' \
--data '{
    "studentId": 1,
    "studyClassId": 1
}'
```

**3. Delete a Subscription (Unenroll a Student)**
```bash
curl --location --request DELETE 'http://localhost:8080/subscriptions/1'
```