FROM marvambass/apache2-ssl-secure

# En caso de problemas...
LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="Apache SSL Load Balancer"


# ------------------------------------------------------------------------------
#                          Proxy Support
# ------------------------------------------------------------------------------
RUN  echo 'Acquire::http { Proxy "http://89.167.129.32:80/"; };' >> /etc/apt/apt.conf.d/01proxy
RUN  echo 'Acquire::https { Proxy "http://89.167.129.32:80/"; };' >> /etc/apt/apt.conf.d/01proxy
RUN  echo 'APT::Get::Assume-Yes "true";' >> /etc/apt/apt.conf && echo 'APT::Get::force-yes "true";' >> /etc/apt/apt.conf

# ------------------------------------------------------------------------------
#                           Other libraries
# ------------------------------------------------------------------------------
RUN requirements="vim" \
    && apt-get update && apt-get install -y --no-install-recommends $requirements \
    && rm -rf /var/lib/apt/lists/*

# ------------------------------------------------------------------------------
#                           SSL Certs
# ------------------------------------------------------------------------------
RUN openssl req -x509 -newkey rsa:4086 \
    -keyout /etc/apache2/external/cert.pem \
    -out /etc/apache2/external/cert.pem \
    -days 3650 -nodes -sha256 \
    -subj "/C=ES/ST=Madrid/L=Madrid/O=Security/OU=Development/CN=casdev.company.com"

# ------------------------------------------------------------------------------
#                           My Config
# ------------------------------------------------------------------------------
RUN echo '# Set server name' >> /etc/apache2/apache2.conf && \
    echo 'ServerName casdev.company.com' >> /etc/apache2/apache2.conf

ADD src/ /var/www/html
