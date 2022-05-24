FROM openjdk:11-jdk
EXPOSE 8081
ARG JAR_FILE=gradle/wrapper/*.jar
COPY ${JAR_FILE} app.jar ./
ENTRYPOINT ["java", "-Xmx200m", "-jar","/app.jar"]