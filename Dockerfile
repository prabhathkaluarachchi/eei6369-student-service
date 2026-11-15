# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Minimal Runtime Image with Non-Root User
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create an unprivileged non-root user and group
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy only the built JAR from builder stage
COPY --from=builder /build/target/*.jar app.jar
RUN chown -R appuser:appgroup /app

# Run container as non-root user
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]