FROM maven:3.5-jdk-8-alpine AS BUILD

WORKDIR /root

ENV DB_URL=jdbc:postgresql://db:5432/tcc_match

COPY src src/
COPY pom.xml .

RUN mvn -f /root/pom.xml clean package

FROM java:8

WORKDIR /root

COPY --from=BUILD /root/target/match.tcc-0.0.1-SNAPSHOT.jar .

ENV PROFILE=prod

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "match.tcc-0.0.1-SNAPSHOT.jar", "-X", ">", "debug.log", "2>", "error.log"]
