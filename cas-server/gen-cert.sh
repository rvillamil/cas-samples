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
keystore_path="/etc/cas/caskeystore"
cas_cert_path="/etc/cas/cas.crt"
deploy_path="/etc/cas/"
cas_cert_alias="casCert"
store_pass="changeit"

# Si no encuentra el comando SUDO suponemos que somos root
echo "0 - Locating sudo command..."
CMD_SUDO=$(whereis sudo | cut -d ' ' -f 2 | grep -v ":")
echo "SUDO_COMMAND: ${CMD_SUDO}"

echo "1 - Deleting previous alias in cacerts ..."
${CMD_SUDO} rm -fv ${keystore_path}
${CMD_SUDO} rm -f ${cas_cert_path}
${CMD_SUDO} keytool -delete -alias ${cas_cert_alias} -keystore ${current_path_certs} -storepass ${store_pass}


# Generamos el certificado autofirmado, el almacen de claves y guardasmo el certificado autofirmado en dicho almacen
DNAME="${DNAME:-CN=localhost,OU=casdev.company.com,O=casdev.company.com,C=ES}"

echo "2 - Generating keystore ${keystore_path} for CAS with DNAME '${DNAME}'"
keytool -genkey -noprompt -keyalg RSA -alias ${cas_cert_alias} -keypass ${store_pass} -keystore ${keystore_path} -storepass ${store_pass} -validity 360 -keysize 2048 -dname ${DNAME}
echo "3 - Exporting cert on ${cas_cert_path}..."
# Exportamos el certificado autofirmado anteriormente generado para importarlo en el 'truststore' o 'cacerts'
keytool -export -alias ${cas_cert_alias} -file ${cas_cert_path} -keystore ${keystore_path} -storepass ${store_pass}
echo "4 - Importing cert on ${current_path_certs}"
${CMD_SUDO} keytool -import -alias ${cas_cert_alias} -storepass ${store_pass} -file ${cas_cert_path} -keystore ${current_path_certs} -noprompt




