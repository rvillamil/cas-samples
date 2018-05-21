# Image base: https://hub.docker.com/r/frolvlad/alpine-oraclejdk8/
FROM openjdk:8-jre-alpine

# En caso de problemas...
LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="CAS client docker container for testing purposes"

# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application
# creates working directories for Tomcat by default.
# The effect is to create a temporary file on your host under "/var/lib/docker" and
# link it to the container under "/tmp".
# This step is optional for the simple app that we wrote here, but
# can be necessary for other Spring Boot applications if they need to actually write in the filesystem.
VOLUME /tmp

ADD /target/*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

# Nos permite esperar a que la aplicacion arranque cuando tenga un mysql
ADD utils/wrapper.sh wrapper.sh
RUN sh -c 'chmod +x /wrapper.sh'

RUN sh -c 'touch /app.jar'
ENTRYPOINT ["sh", "/wrapper.sh"]
