
FROM openjdk:8

ADD target/workshop.jar workshop.jar

ENTRYPOINT ["java", "-jar","workshop.jar"]


EXPOSE 8080