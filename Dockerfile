FROM openjdk:11
EXPOSE 8080
COPY target/expense-tracker.jar expense-tracker.jar
ENTRYPOINT ["java", "-jar", "/expense-tracker.jar"]