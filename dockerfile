FROM maven:3.9-eclipse-temurin-21
WORKDIR /app
COPY /home/ubuntu/workspace/PipelineDockerJob/target/jar-demo-1.0.jar .
EXPOSE 8080
CMD ["java","-jar","jar-demo-1.0.jar"]
