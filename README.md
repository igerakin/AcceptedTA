
# Technical Assessment - Sports Betting API

A RESTful API for managing sports matches and betting odds built with Spring Boot 3.3.3 and Java 21.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Quick Start](#quick-start)
- [Docker Deployment](#docker-deployment)
- [API Documentation](#api-documentation)
- [Database](#database)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Sample Data](#sample-data)
- [Development](#development)
- [Contributing](#contributing)

## Overview

This application provides a comprehensive API for managing sports matches and their associated betting odds. It supports multiple sports (Football and Basketball) and allows full CRUD operations on matches and odds data.

## Features

- ✅ **Match Management**: Create, read, update, and delete sports matches
- ✅ **Odds Management**: Manage betting odds for different match outcomes
- ✅ **Multi-Sport Support**: Football and Basketball with different betting types
- ✅ **RESTful API**: Clean REST endpoints with proper HTTP status codes
- ✅ **Interactive Documentation**: Swagger UI for API exploration
- ✅ **In-Memory Database**: H2 database with web console for development
- ✅ **Sample Data**: Pre-loaded with realistic match and odds data
- ✅ **Hot Reload**: Development-friendly with automatic restart
- ✅ **Docker Support**: Fully containerized with Docker and Docker Compose
- ✅ **Health Checks**: Built-in health monitoring endpoints

## Technology Stack

- **Java 21** (Preview features enabled)
- **Spring Boot 3.3.3**
- **Spring Data JPA** - Data persistence
- **Spring Web** - RESTful web services
- **Spring Boot Actuator** - Health checks and monitoring
- **H2 Database** - In-memory database
- **Lombok** - Code generation
- **Swagger/OpenAPI 3** - API documentation
- **Docker** - Containerization
- **Maven** - Dependency management

## Quick Start

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- Docker & Docker Compose (for containerized deployment)

### Running Locally

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd technical-assessment
   ```

2. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the application**
   - API Base URL: `http://localhost:8080/api`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`
   - Health Check: `http://localhost:8080/actuator/health`

## Docker Deployment

### Option 1: Docker Compose (Recommended)

1. **Start the application**
   ```bash
   docker-compose up -d
   ```

2. **View logs**
   ```bash
   docker-compose logs -f
   ```

3. **Stop the application**
   ```bash
   docker-compose down
   ```

### Option 2: Docker Commands

1. **Build the image**
   ```bash
   docker build -t technical-assessment:latest .
   ```

2. **Run the container**
   ```bash
   docker run -d \
     --name technical-assessment-app \
     -p 8080:8080 \
     -v $(pwd)/logs:/app/logs \
     technical-assessment:latest
   ```

3. **Stop the container**
   ```bash
   docker stop technical-assessment-app
   docker rm technical-assessment-app
   ```

### Option 3: Helper Scripts

Make scripts executable:
```bash
chmod +x docker-build.sh docker-run.sh docker-stop.sh
```
