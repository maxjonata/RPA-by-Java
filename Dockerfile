FROM microsoft-windows

WORKDIR /rpa-by-java

COPY . .





FROM maven
RUN mvn package






FROM openjdk

EXPOSE 8080

RUN mvn exec:java