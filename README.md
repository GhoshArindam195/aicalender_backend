# AI Holiday Calendar Backend

A Spring Boot REST API application for managing Indian state-wise holidays. This backend service provides APIs to manage states, holidays, and holiday instances across different Indian states.

## Tech Stack

- **Framework**: Spring Boot 3.3.5
- **Language**: Java 17
- **Database**: MySQL 8.x
- **Build Tool**: Maven
- **API Documentation**: OpenAPI/Swagger
- **Object Mapping**: MapStruct
- **Email**: Spring Mail (SMTP)

## Features

- Manage Indian states (CRUD operations)
- Get holidays by state and year
- Create holiday calendars with multiple states
- Support for National and Regional holiday types
- Optional holiday support
- RESTful API with OpenAPI documentation

## Project Structure

```
alcalender_backend/
├── src/main/java/com/holidays/alcalender_backend/
│   ├── AlcalenderBackendApplication.java     # Main application entry
│   ├── controller/                            # REST controllers
│   │   ├── HolidayController.java
│   │   └── StateController.java
│   ├── service/                               # Business logic
│   │   ├── HolidayService.java
│   │   └── StateService.java
│   ├── entity/                                # JPA entities
│   │   ├── State.java
│   │   ├── Holiday.java
│   │   ├── HolidayInstance.java
│   │   └── HolidayType.java
│   ├── dto/                                   # Data transfer objects
│   │   ├── StateDto.java
│   │   ├── HolidayDto.java
│   │   ├── HolidayInstanceDto.java
│   │   ├── HolidayCalendarDto.java
│   │   └── StateHolidaysDto.java
│   ├── mapper/                                # MapStruct mappers
│   │   ├── StateMapper.java
│   │   ├── HolidayMapper.java
│   │   └── HolidayInstanceMapper.java
│   ├── repository/                           # Spring Data repositories
│   │   ├── StateRepository.java
│   │   ├── HolidayRepository.java
│   │   └── HolidayInstanceRepository.java
│   └── exception/                             # Exception handling
│       ├── GlobalExceptionHandler.java
│       └── ResourceNotFoundException.java
├── src/main/resources/
│   ├── application.properties                 # Main configuration
│   └── application.properties.template        # Configuration template
├── src/test/                                  # Test files
└── pom.xml                                    # Maven dependencies
```

## API Endpoints

### State Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/states` | Get all states |

### Holiday Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/holidays/{state}/{year}` | Get holidays for a state in a given year |
| POST | `/api/holidays` | Create holiday calendar |

## Configuration

### Database Configuration

Edit `src/main/resources/application.properties`:

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/holiday_calendar
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Server Configuration

```properties
server.port=8081
```

### Email Configuration (Optional)

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Setup Instructions

### Prerequisites

- Java 17 or higher
- MySQL 8.x
- Maven 3.8+

### Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE holiday_calendar;
```

2. Update the database credentials in `application.properties`

### Build and Run

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8081`

## API Documentation

Once the application is running, access the Swagger UI:

- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8081/api-docs

## Example API Requests

### Get All States

```bash
curl -X GET http://localhost:8081/api/states
```

### Get Holidays for a State

```bash
curl -X GET http://localhost:8081/api/holidays/UP/2024
```

### Create Holiday Calendar

```bash
curl -X POST http://localhost:8081/api/holidays \
  -H "Content-Type: application/json" \
  -d '{
    "year": 2024,
    "states": [
      {
        "code": "UP",
        "name": "Uttar Pradesh",
        "holidays": [
          {
            "name": "Republic Day",
            "holidayType": "NATIONAL",
            "date": "2024-01-26",
            "isOptional": false
          }
        ]
      }
    ]
  }'
```

## Domain Model

### Entities

- **State**: Represents Indian states (id, name, code)
- **Holiday**: Represents holiday definitions (name, description, holidayType)
- **HolidayInstance**: Links Holiday + State + Date (actual occurrence)
- **HolidayType**: Enum - NATIONAL, REGIONAL

## License

This project is for educational purposes.
