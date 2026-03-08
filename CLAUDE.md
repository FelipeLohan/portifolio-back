# Portfolio Backend — Project Guide

## Stack & Tooling

- **Java 21** + **Spring Boot 3.5.11**
- **Maven** (wrapper: `./mvnw`)
- **PostgreSQL 16** (via Docker)
- **Lombok**, **MapStruct 1.6.3**, **Springdoc OpenAPI 2.8.16**
- **Spring Validation**, **Spring Data JPA**

## Running the Project

```bash
# Start PostgreSQL + PgAdmin
docker-compose up -d

# Build and run
./mvnw spring-boot:run

# Run tests
./mvnw test
```

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- PgAdmin: http://localhost:5433

## Database Config

- URL: `jdbc:postgresql://localhost:5432/mydb`
- User: `admin` / Password: `admin123`
- DDL: `spring.jpa.hibernate.ddl-auto=update`

## Package Structure

```
com.FelipeLohan.portifolio
├── PortifolioApplication.java
├── customer/
│   ├── Customer.java               # JPA entity
│   ├── CustomerController.java     # REST controller (implements CustomerControllerI)
│   ├── CustomerService.java        # Business logic (implements CustomerServiceI)
│   ├── CustomerRepository.java     # JPA repository
│   ├── dtos/
│   │   ├── RequestCustomerDTO.java
│   │   └── ResponseCustomerDTO.java
│   ├── interfaces/
│   │   ├── CustomerControllerI.java  # Swagger docs live here
│   │   └── CustomerServiceI.java
│   └── mapper/
│       └── CustomerMapper.java      # MapStruct
└── exception/
    ├── GlobalExceptionHandler.java  # @RestControllerAdvice
    └── ErrorResponseDTO.java
```

## Architecture Conventions

- **Interface segregation**: Controllers and services implement interfaces. Swagger `@Operation`/`@ApiResponse` annotations go on the **interface**, not the implementation.
- **DTO pattern**: Use Java `record` types for DTOs. Request and Response DTOs are separate.
- **Mapping**: Use **MapStruct** (`CustomerMapper`) for entity ↔ DTO conversion. Never map manually in controllers or services.
- **Validation**: Declare `@Valid` constraints on `RequestCustomerDTO` fields. Controllers accept `@Valid @RequestBody`.
- **Exception handling**: Throw `EntityNotFoundException` for 404s. `GlobalExceptionHandler` handles it centrally.
- **Transactions**: Use `@Transactional` on service write methods.

## Adding a New Feature Module

Follow the `customer` module as a template:

1. `Entity` class with `@Entity`
2. `Repository` extending `JpaRepository`
3. `RequestDTO` and `ResponseDTO` records with validation
4. `Mapper` interface annotated with `@Mapper(componentModel = "spring")`
5. `ServiceI` interface + `Service` implementation
6. `ControllerI` interface (with Swagger) + `Controller` implementation
7. Register Swagger `@Tag` on the controller interface

## Build Notes

- Annotation processors order matters: Lombok must run **before** MapStruct. The `pom.xml` configures this via `lombok-mapstruct-binding`.
- Do **not** remove `annotationProcessorPaths` from the `maven-compiler-plugin`.
