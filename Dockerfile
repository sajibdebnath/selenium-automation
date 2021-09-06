# Linux base image
FROM maven:3.6.0-jdk-13
MAINTAINER Faizul Islam (faizulcse@gmail.com)

USER root
WORKDIR /app
COPY src /app/src
COPY *.xml /app
COPY screenshots /app/screenshots