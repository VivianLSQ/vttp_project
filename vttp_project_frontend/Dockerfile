FROM node:18-alpine3.17 AS angbuilder

WORKDIR /app

COPY vttp_project_frontend/angular.json .
COPY vttp_project_frontend/package.json .
COPY vttp_project_frontend/package-lock.json .
COPY vttp_project_frontend/tsconfig.app.json .
COPY vttp_project_frontend/tsconfig.spec.json .
COPY vttp_project_frontend/tsconfig.json .
COPY vttp_project_frontend/src src

RUN npm i -g @angular/cli
RUN npm ci
RUN ng build

FROM maven:3.8.5-openjdk-17 AS javabuilder

WORKDIR /app

COPY vttp_project_backend/src src
COPY vttp_project_backend/mvnw .
COPY vttp_project_backend/pom.xml .

COPY --from=angbuilder /app/dist/vttp_project_frontend /app/src/main/resources/static

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=javabuilder /app/target/vttp_project_backend.jar /app.jar

ARG RAILWAY_ENVIRONMENT
ENV RAILWAY_ENVIRONMENT=$RAILWAY_ENVIRONMENT

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar /app/app.jar
