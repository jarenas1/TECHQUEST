# RIWI TechQuest - Mission and Skills Management Platform

## Introduction
RIWI TechQuest is a platform designed to manage the training of students, referred to as "heroes in training," by assigning missions and special skills based on their progress. The system ensures role-based permissions, robust security using JWT authentication, and automatic auditing to track key actions within the system.

## Features
- **Roles and Permissions**:
  - **ADMIN**: Full control over the platform (users, missions, and skills).
  - **TEACHER**: Manage missions and skills for assigned students.
  - **STUDENT**: View assigned missions and skills.
- **Skill and Mission Management**:
  - Assign multiple skills to students and manage missions with varying difficulty levels (Easy, Medium, Hard).
- **Auditing System**: Automatically tracks changes made to missions, students, and skills.
- **Exception Handling**: Captures and manages errors such as unauthorized access, resource not found, and validation errors.
- **API Documentation**: Full Swagger documentation for all API endpoints.

## Tech Stack
- **Backend**: Spring Boot (Java)
- **Database**: MySQL or PostgreSQL (multi-database support)
- **Authentication**: JWT (JSON Web Token)
- **Auditing**: Spring Data JPA
- **API Documentation**: Swagger
- **Optional**: Cloudinary, Amazon S3, or Google Cloud for multimedia storage

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL or PostgreSQL
- Git
- Optional: Cloudinary, AWS, or Google Cloud account for multimedia storage

## Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/riwi-techquest.git
   cd riwi-techquest
```

2. **Configure the environment variables**:
```bash
JWT_SECRET=your_jwt_secret
DATABASE_URL=your_database_url
CLOUDINARY_API_KEY=your_cloudinary_key   # If using Cloudinary

```

Database setup:

For MySQL:

bash

Copiar
spring.datasource.url=jdbc:mysql://localhost:3306/techquest
spring.datasource.username=root
spring.datasource.password=yourpassword
For PostgreSQL:

bash

Copiar
spring.datasource.url=jdbc:postgresql://localhost:5432/techquest
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
Run the application: Compile and run the Spring Boot application using Maven:

bash

Copiar
mvn spring-boot:run
Access the API: Once the server is running, the API will be accessible at http://localhost:8080. You can explore the Swagger documentation by visiting:

bash

Copiar
http://localhost:8080/swagger-ui.html
Usage
1. Authentication
Use the following endpoint to log in and receive a JWT token:

POST /api/v1/auth/login Example request body:

json

Copiar
{ 
  "email": "user@example.com", 
  "password": "password123" 
}
Assign Skills to Students Teachers can assign skills to students using the following endpoint: POST /api/v1/students/{id}/abilities

Create and Assign Missions Teachers can create missions and assign them to students based on their skills: POST /api/v1/missions

Deployment
You can deploy the application to free hosting services like Railway or Render. Follow their respective documentation to deploy Spring Boot applications.

Example deployment steps for Railway:

Create a new project on Railway.

Connect your GitHub repository.

Set environment variables on Railway based on your .env file.

Deploy the application.

Optional Features
Multi-Database Support: The application can switch between MySQL and PostgreSQL using Spring profiles.

Granular Permissions: Detailed permission control beyond basic roles can be added.

External API for File Storage: Integrate Cloudinary, Amazon S3, or Google Cloud Storage for storing multimedia files associated with missions.

Testing
Unit and integration tests are available. To run the tests, use the following Maven command:

bash

Copiar
mvn test
Contributing
Contributions are welcome! Please follow these steps:

Fork the project.

Create your feature branch (git checkout -b feature/AmazingFeature).

Commit your changes (git commit -m 'Add some AmazingFeature').

Push to the branch (git push origin feature/AmazingFeature).

Open a Pull Request.


