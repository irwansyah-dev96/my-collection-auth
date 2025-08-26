# RUN
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/readcollection-auth-0.0.1.jar  myapp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "myapp.jar"]

