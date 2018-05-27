#Choose Debian
FROM debian:latest

# En caso de problemas...
LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="Apache reverse proxy with SSL support"

# ------------------------------------------------------------------------------
#                           Install debian packages
# ------------------------------------------------------------------------------
#Don't ask questions during install
ENV DEBIAN_FRONTEND noninteractive

RUN requirements="apache2 vim openssl curl" \
    && apt-get update && apt-get install -y --no-install-recommends $requirements \
    && rm -rf /var/lib/apt/lists/*


# ------------------------------------------------------------------------------
#                           SSL Certs
# ------------------------------------------------------------------------------
RUN mkdir -p /etc/apache2/external && \
    echo ">> generating self signed cert" && \
    openssl req -x509 -newkey rsa:4086 \
    -keyout /etc/apache2/external/key.pem \
    -out /etc/apache2/external/cert.pem \
    -days 3650 -nodes -sha256 \
    -subj "/C=ES/ST=Madrid/L=Madrid/O=Security/OU=Development/CN=casdev.company.com"

#
# Redirect Log to console
#
RUN ln -sf /proc/self/fd/1 /var/log/apache2/access.log && \
    ln -sf /proc/self/fd/1 /var/log/apache2/error.log
# ------------------------------------------------------------------------------
#                           My Config
# ------------------------------------------------------------------------------
# Remove Default sites
RUN mv /etc/apache2/sites-enabled/* /tmp
RUN mv /etc/apache2/sites-available/* /tmp

# Copy virtualhost on apache directory
ADD etc/apache2/sites-available/ /etc/apache2/sites-available

RUN echo '# Set server name' >> /etc/apache2/apache2.conf && \
    echo 'ServerName casdev.company.com' >> /etc/apache2/apache2.conf

RUN a2enmod proxy \
    && a2enmod proxy_http \
    && a2enmod ssl \
    && a2enmod rewrite \
    && a2enmod headers \
    && service apache2 stop

# Enable my site
RUN a2ensite 000-casdev-site

# Ports
EXPOSE 80 443

# Launch Apache2 on FOREGROUND
COPY etc/start.sh /opt/
RUN chmod +x /opt/start.sh
ENTRYPOINT ["/opt/start.sh"]

