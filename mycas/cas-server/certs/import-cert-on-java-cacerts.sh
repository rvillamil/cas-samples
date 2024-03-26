#!/usr/bin/env bash

source ../../functions-common.sh

# Si no encuentra el comando SUDO suponemos que somos root
CMD_SUDO=$(whereis sudo | cut -d ' ' -f 2 | grep -v ":")
logInfo "Locating sudo command...:'${CMD_SUDO}'" 1

logInfo "Deleting previous alias in cacerts ..." 1
keytool -delete -alias ${CAS_CERT_ALIAS} -keystore ${JAVA_CACERTS_PATH} -storepass ${CAS_KEYSTORE_PASSWORD}

logInfo "Import the certificate into the global keystore" 1
keytool -import -file ${MYCERT_PATH} -alias ${CAS_CERT_ALIAS} -keystore ${JAVA_CACERTS_PATH} -storepass ${CAS_KEYSTORE_PASSWORD} -noprompt




