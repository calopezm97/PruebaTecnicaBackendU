FROM openjdk:16-alpine3.13

WORKDIR /app

COPY . .
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/PruebaTecnicaBackendU-0.0.1-SNAPSHOT.jar"]