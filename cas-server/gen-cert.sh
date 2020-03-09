#!/bin/bash
#
# Copyright (C) Rodrigo Villamil Perez 2018
# Fichero: gen-cert.sh
# Fecha: 03/05/18
#
# https://www.adictosaltrabajo.com/tutoriales/security-ssl-keytool/
# https://www.adictosaltrabajo.com/tutoriales/autenticacion-ssl-con-certificado-de-cliente/
#
path_cacerts="${JAVA_HOME}/jre/lib/security/cacerts"
current_path_certs=${path_cacerts}
keystore_path="cas-server-overlay/etc/cas/caskeystore"
cas_cert_path="cas-server-overlay/etc/cas/cas.crt"
deploy_path="cas-server-overlay/etc/cas/"
cas_cert_alias="casCert"
store_pass="changeit"

echo "Deleting previous alias in cacerts ..."
sudo rm -fv ${keystore_path}
sudo rm -f ${cas_cert_path}
sudo keytool -delete -alias ${cas_cert_alias} -keystore ${current_path_certs} -storepass ${store_pass}


# Generamos el certificado autofirmado, el almacen de claves y guardasmo el certificado autofirmado en dicho almacen
DNAME="${DNAME:-CN=casdev.company.com,OU=casdev.company.com,O=casdev.company.com,C=ES}"

echo "Generating keystore ${keystore_path} for CAS with DNAME '${DNAME}'"
keytool -genkey -keyalg RSA -alias ${cas_cert_alias} -keystore ${keystore_path} -storepass ${store_pass} -validity 360 -keysize 2048 -dname ${DNAME}

# Exportamos el certificado autofirmado anteriormente generado para importarlo en el 'truststore' o 'cacerts'
keytool -export -alias ${cas_cert_alias} -file ${cas_cert_path} -keystore ${keystore_path}
sudo keytool -import -alias ${cas_cert_alias} -storepass ${store_pass} -file ${cas_cert_path} -keystore ${current_path_certs}




