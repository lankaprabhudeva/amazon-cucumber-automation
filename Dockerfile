FROM maven:3.9.4-eclipse-temurin-17
WORKDIR /app
COPY . .
CMD ["mvn", "clean", "test"]
