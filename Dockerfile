FROM openjdk:15
ADD target/cinemaCMS.jar cinemaCMS.jar
EXPOSE 25565
ENTRYPOINT ["java", "-jar", "cinemaCMS.jar"]