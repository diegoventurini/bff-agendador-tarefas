FROM maven:3.9-amazoncorretto-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/bff-agendador-tarefas.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]

