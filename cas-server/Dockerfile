# Image base: https://hub.docker.com/r/frolvlad/alpine-oraclejdk8/
FROM tomcat:8.5.51-jdk8-openjdk

LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="Aepero CAS docker container for testing purposes"

# Tools
#RUN apt install sudo

# CAS-SERVER
ADD cas-server-overlay/target/cas.war /usr/local/tomcat/webapps/
ADD cas-server-overlay/etc/cas/ /etc/cas/
COPY docker/server.xml /usr/local/tomcat/conf/

# CAS-SERVER-MANAGEMENT
ADD cas-services-management-overlay/target/cas-management.war /usr/local/tomcat/webapps/
ADD cas-services-management-overlay/etc/cas/config/ /etc/cas/config/
ADD gen-cert.sh /etc/cas
RUN sh /etc/cas/gen-cert.sh

EXPOSE 8443

CMD ["catalina.sh", "run"]
