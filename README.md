# Quiz-App-SpringBoot-Basic-Microservices-App

# Quiz App: Spring Boot Microservices

A quiz application built with **Spring Boot microservices**. Users can create quizzes from stored questions, fetch quiz questions, and submit answers to get a score. The services communicate through **Eureka service discovery**, **OpenFeign**, and a **Spring Cloud API Gateway**.

## Architecture

```
                 Client (Postman / Browser)
                            │
                    ┌───────▼────────┐
                    │  API Gateway   │
                    └───┬────────┬───┘
                        │        │
              ┌─────────▼──┐  ┌──▼──────────────┐
              │Quiz Service│─▶│ Question Service│
              └─────────┬──┘  └──┬──────────────┘
         (OpenFeign call)        │
                        └────┬───┘
                    ┌────────▼────────┐
                    │ Service Registry│
                    │    (Eureka)     │
                    └─────────────────┘
```

## Services

| Service | Folder | Port | Description |
|---|---|---|---|
| Service Registry | `Service-Registry-QuizApp` | `<8761>` | Eureka server where all services register |
| API Gateway | `Api-Gateway-QuizApp` | `<8765>` | Single entry point that routes requests to services |
| Question Service | `Question-Service-QuizApp` | `<8080>` | Manages questions, generates quiz question IDs, calculates scores |
| Quiz Service | `Quiz-Service-QuizApp` | `<8090>` | Creates quizzes and calls Question Service through Feign |

## Tech Stack

- Java `<17>`
- Spring Boot
- Spring Cloud (Eureka, Gateway, OpenFeign)
- Spring Data JPA
- Database: `<PostgreSQL / MySQL / H2>`
- Maven

## Project Structure

```
Quiz-App/
├── Api-Gateway-QuizApp/
├── Question-Service-QuizApp/
├── Quiz-Service-QuizApp/
└── Service-Registry-QuizApp/
```

## Getting Started

### Prerequisites

- JDK `<17>` or higher
- Maven (or use the included `mvnw`)
- `<Database>` running locally

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Krishna010804priya/Quiz-App-SpringBoot-Basic-Microservices-App.git
   cd Quiz-App-SpringBoot-Basic-Microservices-App
   ```

2. Configure the database in `Question-Service-QuizApp/src/main/resources/application.properties` and `Quiz-Service-QuizApp/src/main/resources/application.properties`.

3. Start the services **in this order**:

   ```bash
   # 1. Service Registry
   cd Service-Registry-QuizApp
   ./mvnw spring-boot:run

   # 2. Question Service
   cd ../Question-Service-QuizApp
   ./mvnw spring-boot:run

   # 3. Quiz Service
   cd ../Quiz-Service-QuizApp
   ./mvnw spring-boot:run

   # 4. API Gateway
   cd ../Api-Gateway-QuizApp
   ./mvnw spring-boot:run
   ```
   On Windows use `mvnw.cmd` instead of `./mvnw`.

4. Open the Eureka dashboard at `http://localhost:<8761>` to confirm all services are registered.

## API Endpoints

All requests go through the gateway: `http://localhost:<8765>`

### Question Service

| Method | Endpoint | Description |
|---|---|---|
| GET | `/question/allQuestions` | Get all questions |
| GET | `/question/category/{category}` | Get questions by category |
| POST | `/question/add` | Add a new question |
| GET | `/question/generate` | Generate random question IDs for a quiz |
| POST | `/question/getQuestions` | Get questions for a list of IDs |
| POST | `/question/getScore` | Calculate score from submitted responses |

### Quiz Service

| Method | Endpoint | Description |
|---|---|---|
| POST | `/quiz/create` | Create a quiz (category, number of questions, title) |
| GET | `/quiz/get/{id}` | Get quiz questions by quiz ID |
| POST | `/quiz/submit/{id}` | Submit answers and get the score |



## Example Request

Create a quiz:

```json
POST /quiz/create
{
  "categoryName": "Java",
  "numQuestions": 5,
  "title": "Java Basics"
}
```

## Author

**Priya**: [GitHub](https://github.com/Krishna010804priya)
