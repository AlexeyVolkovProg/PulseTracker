FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY target/bot-0.1.jar /app/bot.jar
ENTRYPOINT ["java", "-jar", "bot.jar"]
EXPOSE 8090