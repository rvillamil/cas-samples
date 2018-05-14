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
# mod-auth-cas instalation
#
ENV MOD_AUTH_CAS_INSTALL_DIR /tmp/mod_auth_cas/
ENV MOD_AUTH_CAS_VERSION mod_auth_cas-1.1
RUN mkdir -p "$MOD_AUTH_CAS_INSTALL_DIR"
COPY resources/"$MOD_AUTH_CAS_VERSION".tar.gz "$MOD_AUTH_CAS_INSTALL_DIR"
RUN cd "$MOD_AUTH_CAS_INSTALL_DIR" \
    && tar xvf "$MOD_AUTH_CAS_VERSION".tar.gz \
    && cd "$MOD_AUTH_CAS_VERSION" \
    && autoreconf -ivf \
    && ./configure \
    && make \
    && cp src/.libs/mod_auth_cas.so /etc/httpd/modules/mod_auth_cas.so
    
#
# Create de cookie cache for mod-auth-cas
#
RUN mkdir -p /var/cache/httpd/mod_auth_cas \
    && chown apache.apache /var/cache/httpd/mod_auth_cas \
    && chmod 700 /var/cache/httpd/mod_auth_cas

# CentOS uses systemd (instead of init) to manage system resources. Run the command
RUN systemctl enable httpd.service

# Iniciamos el servicio en el puerto 80
EXPOSE 80
CMD ["/usr/sbin/httpd","-D","FOREGROUND"]

    