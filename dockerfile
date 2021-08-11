# syntax=docker/dockerfile:1

FROM gradle:6.8.3 
COPY . /src/java
WORKDIR /src/java
RUN gradle clean build 
# RUN ["javac", "Usermanagement.java"]
ENTRYPOINT java -jar build/libs/user-management-0.0.1-SNAPSHOT.jar 

