FROM jelastic/maven:3.9.5-openjdk-21
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
