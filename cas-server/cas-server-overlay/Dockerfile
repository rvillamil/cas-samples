# Image base: https://hub.docker.com/r/frolvlad/alpine-oraclejdk8/
FROM openjdk:8-jre-alpine

# En caso de problemas...
LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="Aepero CAS docker container for testing purposes"

# Copia el fichero alojado en: vws-resources/target/docker/img-vws-resources/X.Y.Z-SNAPSHOT/build/maven/
COPY target/cas.war /cas.war
# Nos permite esperar a que la aplicacion arranque cuando tenga  la bbdd levantada
COPY utils/wrapper.sh wrapper.sh
RUN sh -c 'chmod +x /wrapper.sh'

RUN sh -c 'touch /cas.war'
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "/wrapper.sh"]
