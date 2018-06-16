FROM centos:7

# En caso de problemas...
LABEL maintainer "Rodrigo Villamil Perez <rodrigo.villamil@gmail.com>" description="CAS Client. Apache2.4 with mod_auth_cas "

# ------------------------------------------------------------------------------
#                          Proxy Support
# ------------------------------------------------------------------------------
#RUN  echo 'Acquire::http { Proxy "http://89.167.129.32:80/"; };' >> /etc/apt/apt.conf.d/01proxy
#RUN  echo 'Acquire::https { Proxy "http://89.167.129.32:80/"; };' >> /etc/apt/apt.conf.d/01proxy
#RUN  echo 'APT::Get::Assume-Yes "true";' >> /etc/apt/apt.conf && echo 'APT::Get::force-yes "true";' >> /etc/apt/apt.conf

# ------------------------------------------------------------------------------
#                           PHP and APACHE support
# ------------------------------------------------------------------------------
RUN requirements="httpd mod_ssl php" \
    && yum install -y $requirements

# ------------------------------------------------------------------------------
#                           PHP Config
# ------------------------------------------------------------------------------
RUN sed -i "s/;date.timezone =/;date.timezone=Europe\/Madrid/g" /etc/php.ini

# ------------------------------------------------------------------------------
#                           SSL support
# ------------------------------------------------------------------------------
RUN openssl req -x509 -nodes -newkey rsa:2048 -sha256 -keyout /etc/pki/tls/private/casapp.key -out /etc/pki/tls/certs/casapp.pem -subj "/C=ES/ST=Madrid/L=Madrid/O=Security/OU=Development/CN=casclient1.company.com"
COPY etc/ssl.conf /etc/httpd/conf.d/ssl.conf

# ------------------------------------------------------------------------------
#                          VIRTUAL HOST
# ------------------------------------------------------------------------------
RUN sed -i "s/#ServerName www.example.com:80/ServerName casclient1.company.com\nUseCanonicalName on/g" /etc/httpd/conf/httpd.conf

RUN echo '# Add by me ' >> /etc/httpd/conf/httpd.conf \
    && echo '<VirtualHost *:80>' >> /etc/httpd/conf/httpd.conf \
    && echo 'Redirect permanent / https://casclient1.company.com/' >> /etc/httpd/conf/httpd.conf \
    && echo '</VirtualHost>' >> /etc/httpd/conf/httpd.conf

# ------------------------------------------------------------------------------
#                           MOD_AUTH_CAS_SUPPORT
# ------------------------------------------------------------------------------
RUN requirements="httpd-devel openssl-devel libcurl-devel autoconf automake libtool" \
    && yum update -y \
    && yum install -y $requirements

# mod-auth-cas support from sources: https://iam.uconn.edu/mod_auth_cas-installation-and-configuration/
ENV MOD_AUTH_CAS_INSTALL_DIR /tmp/mod_auth_cas/
ENV MOD_AUTH_CAS_VERSION mod_auth_cas-master_28_11_2017
RUN mkdir -p "$MOD_AUTH_CAS_INSTALL_DIR"
COPY resources/cas/"$MOD_AUTH_CAS_VERSION".tar.gz "$MOD_AUTH_CAS_INSTALL_DIR"
RUN cd "$MOD_AUTH_CAS_INSTALL_DIR" \
    && tar xvf "$MOD_AUTH_CAS_VERSION".tar.gz \
    && cd mod_auth_cas-master \
    && autoreconf -ivf \
    && ./configure \
    && make \
    && cp src/.libs/mod_auth_cas.so /etc/httpd/modules/mod_auth_cas.so

COPY etc/cas.conf /etc/httpd/conf.d/

RUN mkdir -p /var/cache/httpd/mod_auth_cas \
    && chown apache.apache /var/cache/httpd/mod_auth_cas \
    && chmod 700 /var/cache/httpd/mod_auth_cas

# ------------------------------------------------------------------------------
#                           My web
# ------------------------------------------------------------------------------
ADD src/casclient/ /var/www/html

# ------------------------------------------------------------------------------
#                           Clean and finish
# ------------------------------------------------------------------------------
RUN systemctl enable httpd.service
EXPOSE 80
EXPOSE 443

# Simple startup script to avoid some issues observed with container restart
ADD resources/run-httpd.sh /run-httpd.sh
RUN chmod -v +x /run-httpd.sh

# Clean yum packages
RUN yum clean all \
    && rm -rf /var/cache/yum

CMD ["/run-httpd.sh"]
