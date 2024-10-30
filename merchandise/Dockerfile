FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/merchandise-0.0.1.jar
COPY ${JAR_FILE} merchandise.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "merchandise.jar"]
