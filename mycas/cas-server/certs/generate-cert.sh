#!/usr/bin/env bash

source ../../functions-common.sh

server_name="mycas.localhost.local"
san_dns="SAN=DNS:mycas.localhost.local,DNS:mycas.localhost.svc.cluster.local,DNS:localhost,DNS:cluster.local,IP:127.0.0.1"

# Limpiamos el viejo certificado
logInfo "Removing old certificate in output dir '${CERTS_OUTPUT_PATH}'" 1
rm -rf ${CERTS_OUTPUT_PATH}
mkdir -p ${CERTS_OUTPUT_PATH}

# Si no encuentra el comando SUDO suponemos que somos root
CMD_SUDO=$(whereis sudo | cut -d ' ' -f 2 | grep -v ":")
logInfo "Locating sudo command...:'${CMD_SUDO}'" 1

# Generamos el certificado autofirmado, el almacen de claves y guardasmo el certificado autofirmado en dicho almacen
DNAME="${DNAME:-CN=${server_name},OU=Test,O=Test,C=ES}"

# Export the certificate into a file
logInfo "Generating keystore ${MYKEYSTORE_PATH} for CAS with DNAME '${DNAME}'" 1
keytool -genkey -noprompt -keyalg RSA -alias ${CAS_CERT_ALIAS} -keypass ${CAS_KEYSTORE_PASSWORD}  -keystore ${MYKEYSTORE_PATH} -storepass ${CAS_KEYSTORE_PASSWORD} -validity 999 -dname ${DNAME} -ext ${san_dns}

logInfo "Exporting cert on ${MYCERT_PATH}..." 1
# Exportamos el certificado autofirmado anteriormente generado para poder importarlo posteriormente en el cacerts de java
keytool -export -alias ${CAS_CERT_ALIAS} -file ${MYCERT_PATH} -keystore ${MYKEYSTORE_PATH} -storepass ${CAS_KEYSTORE_PASSWORD}



