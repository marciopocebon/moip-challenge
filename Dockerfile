FROM openjdk:8-jdk-alpine

RUN mkdir /moip
COPY build/libs/moip-challenge-1.0-SNAPSHOT.jar /moip/moip-challenge-1.0-SNAPSHOTjar
WORKDIR /mta

CMD ["sh", "-c", "java -jar moip-challenge-1.0-SNAPSHOT.jar"]
