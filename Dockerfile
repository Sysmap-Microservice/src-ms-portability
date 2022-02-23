FROM openjdk:17.0.2-jdk-oracle
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/src-ms-portability.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT java -jar src-ms-portability.jar
