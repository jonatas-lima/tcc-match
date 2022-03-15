FROM maven:3.5-jdk-8-alpine AS BUILD

WORKDIR /root

COPY src src/
COPY pom.xml .

RUN mvn -f /root/pom.xml clean package

FROM java:8

WORKDIR /root

COPY --from=BUILD /root/target/match.tcc-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "match.tcc-0.0.1-SNAPSHOT.jar"]
