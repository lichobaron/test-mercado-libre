FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

COPY target/myapp.jar /app/myapp.jar

CMD ["java", "-jar", "myapp.jar"]