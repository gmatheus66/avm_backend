FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/avm-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} /avm.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/avm.jar"]
