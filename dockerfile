FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} ~/app.jar
ADD src/main/resources/application.properties ~/application.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","~/app.jar"]