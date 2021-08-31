# Linux base image
FROM maven:3.6.0-jdk-13
MAINTAINER Faizul Islam (faizulcse@gmail.com)

WORKDIR /app
USER root
COPY src /app/src
COPY pom.xml /app