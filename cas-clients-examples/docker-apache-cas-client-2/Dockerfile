FROM php:5.6-apache

LABEL maintainer Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>

# ------------------------------------------------------------------------------   
#                          Proxy Support
# ------------------------------------------------------------------------------
RUN  echo 'Acquire::http { Proxy "http://89.167.129.32:80/"; };' >> /etc/apt/apt.conf.d/01proxy
RUN  echo 'Acquire::https { Proxy "http://89.167.129.32:80/"; };' >> /etc/apt/apt.conf.d/01proxy
RUN  echo 'APT::Get::Assume-Yes "true";' >> /etc/apt/apt.conf && echo 'APT::Get::force-yes "true";' >> /etc/apt/apt.conf

# ------------------------------------------------------------------------------   
#                           PHP and Others support
# ------------------------------------------------------------------------------
RUN requirements="vim nano cron mysql-client-5.5 libpng12-dev libmcrypt-dev libmcrypt4 libcurl3-dev libxml2-dev libfreetype6 libjpeg62-turbo libpng12-dev libfreetype6-dev libjpeg62-turbo-dev" \
    && apt-get update && apt-get install -y --no-install-recommends $requirements && rm -rf /var/lib/apt/lists/* \
    && docker-php-ext-install pdo pdo_mysql \
    && docker-php-ext-configure gd --with-freetype-dir=/usr/include/ --with-jpeg-dir=/usr/include/ \
    && docker-php-ext-install gd \
    && docker-php-ext-install mcrypt \
    && docker-php-ext-install mbstring \
    && docker-php-ext-install soap \
    && docker-php-ext-install mysqli


# ------------------------------------------------------------------------------   
#                           MOD_AUTH_CAS_SUPPORT
# ------------------------------------------------------------------------------
RUN requirements_mod_auth_cas="libpcre3 libpcre3-dev libssl-dev apache2-dev libcurl4-openssl-dev dh-autoreconf" \ 
    && apt-get update \
    && apt-get install -y --no-install-recommends $requirements_mod_auth_cas && rm -rf /var/lib/apt/lists/* \
    && apt-get purge --auto-remove -y


# mod-auth-cas support from sources: https://iam.uconn.edu/mod_auth_cas-installation-and-configuration/
ENV MOD_AUTH_CAS_INSTALL_DIR /tmp/mod_auth_cas/
ENV MOD_AUTH_CAS_VERSION mod_auth_cas-master_28_11_2017
RUN mkdir -p "$MOD_AUTH_CAS_INSTALL_DIR"
COPY resources/cas/"$MOD_AUTH_CAS_VERSION".tar.gz "$MOD_AUTH_CAS_INSTALL_DIR"
RUN cd "$MOD_AUTH_CAS_INSTALL_DIR" \
    && tar xvf "$MOD_AUTH_CAS_VERSION".tar.gz \
    && cd mod_auth_cas-master \
    && autoreconf -ivf \    
    && ./configure --with-apxs=/usr/bin/apxs2 \
    && make \
    && make install

RUN mkdir /var/cache/apache2/mod_auth_cas && \
    chown www-data:www-data /var/cache/apache2/mod_auth_cas/    

# ------------------------------------------------------------------------------   
#                           SSL support
# ------------------------------------------------------------------------------
RUN openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/ssl-cert-snakeoil.key -out /etc/ssl/certs/ssl-cert-snakeoil.pem -subj "/C=ES/ST=Madrid/L=Madrid/O=Security/OU=Development/CN=localhost"
RUN a2enmod rewrite
RUN a2enmod ssl

# ------------------------------------------------------------------------------   
#                           My config
# ------------------------------------------------------------------------------
COPY etc/php.ini /usr/local/etc/php/
COPY etc/auth_cas.load /etc/apache2/mods-available/
COPY etc/default-ssl.conf /etc/apache2/sites-available/
RUN a2enmod auth_cas
RUN a2ensite default-ssl
RUN echo '# Set server name' >> /etc/apache2/apache2.conf && echo 'ServerName localhost' >> /etc/apache2/apache2.conf


EXPOSE 80
EXPOSE 443
