# Stage 1: Build the application
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew
RUN ./gradlew build -x test

# Stage 2: Runtime image
FROM eclipse-temurin:17-jre

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app

RUN groupadd -r appuser && useradd -r -g appuser appuser

COPY --from=builder /app/build/libs/*.jar app.jar

RUN chown -R appuser:appuser /app
USER appuser

EXPOSE 8082

HEALTHCHECK CMD curl -f http://localhost:8082/api/users/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
