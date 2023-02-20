FROM eclipse-temurin:18-jdk-alpine

COPY build/libs/book.jar /book.jar

EXPOSE 8081:8081

ENTRYPOINT ["sh", "-c", "java -jar /book.jar"]



