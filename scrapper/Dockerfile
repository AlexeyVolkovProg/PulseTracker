FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY target/scrapper-0.1.jar /app/scrapper.jar
ENTRYPOINT ["java", "-jar", "scrapper.jar"]
EXPOSE 25000