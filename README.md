# Journal Management System

A Spring Boot REST API for managing journal entries with secure user authentication, role-based authorization, caching, and external service integrations.

## Overview

The Journal Management System provides RESTful APIs for users to create, view, update, and delete journal entries.

The application follows a layered architecture with separate Controller, Service, Repository, Configuration, and Utility components. It uses MongoDB for data persistence and Redis for caching.

## Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Data MongoDB
- Spring Security
- JWT
- Redis
- Maven
- Lombok
- Hibernate Validator
- Spring Boot Actuator

## Key Features

### Journal Management
- Create journal entries
- Retrieve journal entries
- Retrieve a journal entry by ID
- Update journal entries
- Delete journal entries

### Authentication & Authorization
- JWT-based authentication
- Role-based access control
- User and Admin roles
- BCrypt password encryption
- Custom JWT authentication filter
- Secured REST endpoints using Spring Security

### Caching
- Redis-based caching
- JSON serialization/deserialization using ObjectMapper
- Configurable cache expiration using TTL

### External Integrations
- Weather API integration
- Email service integration

### Monitoring
- Spring Boot Actuator endpoints for application monitoring

## Architecture

The application follows a layered architecture:
Client
   |
   v
Spring Security
   |
   v
JWT Filter
   |
   v
Controller
   |
   +----> Service ----> MongoDB
   |
   +----> Redis Cache
   |
   +----> External Services


