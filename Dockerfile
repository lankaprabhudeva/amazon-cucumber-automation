# Stage 1: Build the project with tests
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean test

# Optional: You can use a lightweight image for logs/output
# But for simplicity, we just stay in the same layer and print logs directly
