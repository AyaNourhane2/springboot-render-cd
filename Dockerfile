FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Forcer le port 8080 et l'adresse
ENV SERVER_PORT=8080
ENV SERVER_ADDRESS=0.0.0.0

EXPOSE 8080
CMD ["java", "-jar", "app.jar", "--server.port=8080", "--server.address=0.0.0.0"]
