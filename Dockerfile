FROM openjdk:17-alpine
COPY components/services/implementation/build/libs/order-service-implementation-1.0.jar /order-service-implementation-1.0.jar
ENTRYPOINT ["java","-jar","/order-help.jar"]
