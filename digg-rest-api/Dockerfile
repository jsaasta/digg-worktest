FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /usr/src/app
COPY pom.xml ./
COPY src ./src

RUN mvn clean package

FROM registry.access.redhat.com/ubi8/openjdk-21:1.20

WORKDIR /app

COPY --from=build /usr/src/app/target/quarkus-app/ /app/

EXPOSE 8080

CMD ["java", "-jar", "/app/quarkus-run.jar"]