# RUN
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/readcollection-auth-0.0.1.jar  myapp.jar
COPY config/application-auth.properties application.properties
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "myapp.jar"]

