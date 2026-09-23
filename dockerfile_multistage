FROM maven:3.9-eclipse-temurin-21 as builder
WORKDIR /sample-webapp
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /sample-webapp/target/jar-demo-1.0.jar .
EXPOSE 8080
CMD ["java","-jar","jar-demo-1.0.jar"]

