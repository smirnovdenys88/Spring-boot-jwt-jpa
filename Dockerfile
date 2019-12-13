FROM maven:3.6.1-jdk-8-alpine AS builder
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:8-jre
COPY ./wait-for-it.sh /app/wait-for-it.sh
COPY --from=builder /tmp/target/app.jar /app/app.jar
COPY ./start.sh /app/start.sh

WORKDIR /app
VOLUME ["/app"]

RUN apt-get update
RUN apt-get install -y postgresql-contrib
RUN apt-get install -y postgresql

ENTRYPOINT ["./start.sh"]