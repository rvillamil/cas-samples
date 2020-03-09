# Image base: https://hub.docker.com/r/frolvlad/alpine-oraclejdk8/
FROM tomcat:8.5.51-jdk8-openjdk

LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="Aepero CAS docker container for testing purposes"

# CAS-SERVER
ADD cas-server-overlay/target/cas.war /usr/local/tomcat/webapps/
ADD cas-server-overlay/etc/cas/ /etc/cas/
COPY utils/server.xml /usr/local/tomcat/conf/

# CAS-SERVER-MANAGEMENT
ADD cas-services-management-overlay/target/cas-management.war /usr/local/tomcat/webapps/
ADD cas-services-management-overlay/etc/cas/config/ /etc/cas/config/

RUN sh -c 'echo "127.0.0.1 casdev.company.com" >> /etc/hosts'

EXPOSE 8443

CMD ["catalina.sh", "run"]
