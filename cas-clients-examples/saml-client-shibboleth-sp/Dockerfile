# Image base: https://hub.docker.com/r/unicon/shibboleth-sp/
FROM unicon/shibboleth-sp

# En caso de problemas...
LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="SAML2-shibboleth SP docker container for testing purposes"

# Configuracion
COPY /etc/shib.conf /etc/httpd/conf.d/shib.conf
COPY /etc/shibboleth2.xml /etc/shibboleth/shibboleth2.xml

# Web site
ADD /src/ /var/www/html/




