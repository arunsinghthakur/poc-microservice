FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9000
ADD /target/contract-service.jar contract-service.jar

ENTRYPOINT ["java", "-jar", "contract-service.jar"] 