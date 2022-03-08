FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine

COPY target/pong-*.jar /pong.jar
COPY opentelemetry-javaagent.jar opentelemetry-javaagent.jar

# set the startup command to execute the jar
CMD ["java","-javaagent:opentelemetry-javaagent.jar", "-jar", "/pong.jar"]