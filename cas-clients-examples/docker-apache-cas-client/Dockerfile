FROM centos:7
LABEL maintainer "rodrigo.villamil@gmail.com"

#
# Proxy server
#
RUN echo 'proxy=http://10.71.217.240:8080' >> /etc/yum.conf


#
# Packages requiered
#
RUN yum -y install httpd \
    && yum -y install httpd-tools \
    && yum -y install mod_ssl \
    && yum -y install php \
    && yum -y install httpd-devel \
    && yum -y install openssl-devel \
    && yum -y install libcurl-devel \    
    && yum -y groupinstall 'Development Tools'

#
# Copiamos la configuracion de apache
#
ADD etc/httpd/conf/httpd.conf /etc/httpd/conf/
ADD etc/httpd/conf.d /etc/httpd/conf.d/


#
# HTTPS Support
#
COPY resources/ssl/server.crt   /etc/pki/tls/certs/server.crt
COPY resources/ssl/server.key   /etc/pki/tls/private/server.key

#
# mod-auth-cas support
#
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
# && make install \
    && cp src/.libs/mod_auth_cas.so /etc/httpd/modules/mod_auth_cas.so
    
# Create de cookie cache for mod-auth-cas
RUN mkdir -p /var/cache/httpd/mod_auth_cas \
    && chown apache.apache /var/cache/httpd/mod_auth_cas \
    && chmod 700 /var/cache/httpd/mod_auth_cas



# CentOS uses systemd (instead of init) to manage system resources. Run the command
RUN systemctl enable httpd.service

# Iniciamos el servicio en el puerto 80 y 443
EXPOSE 80
EXPOSE 443

CMD ["/usr/sbin/httpd","-D","FOREGROUND"]

    
