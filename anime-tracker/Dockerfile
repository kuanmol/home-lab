# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

# Copy Maven configuration
COPY pom.xml .

# Download dependencies
RUN mvn -B -ntp dependency:go-offline

# Copy application source
COPY src ./src

# Build Spring Boot JAR
RUN mvn -B -ntp clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Application port
EXPOSE 8080

# Run as non-root user
USER 10001

ENTRYPOINT ["java", "-jar", "app.jar"]
