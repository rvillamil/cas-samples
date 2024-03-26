#!/usr/bin/env bash

# Global vars
BASE_PATH="${PWD}/cas-server"

if [ -z "${MY_DOCKER_REGISTRY}" ];then  
  export MY_DOCKER_REGISTRY="localhost:32000"
fi

# About certs ..
CERTS_BASE_PATH="${BASE_PATH}/certs"
CERTS_OUTPUT_PATH="${CERTS_BASE_PATH}/output"
MYCERT_PATH="${CERTS_BASE_PATH}/output/cas.crt"
MYKEYSTORE_PATH="${CERTS_BASE_PATH}/output/thekeystore"
CAS_CERT_ALIAS="mycas"
CAS_KEYSTORE_PASSWORD="changeit"
JAVA_CACERTS_PATH="$JAVA_HOME/lib/security/cacerts" # Almacen de certificados de confianza. Java instalado con SDKMan

# About Docker ...
CONTAINER_CAS_SERVER_NAME="mycas"
IMAGE_TAG="${CONTAINER_CAS_SERVER_NAME}:latest"

# Constants
MONKEY="\U0001F412"
FIRE="\U0001F525"
SMILE="\U0001f602"

logInfo() {
  message="${1}"
  tabs=${2}
  spaces=""
  if [ ! -z ${tabs} ]; then
    x=1
    while [ ${x} -le ${tabs} ]; do
      spaces="${spaces}--"
      x=$(($x + 1))
    done
  fi
  echo -e "${spaces} ${MONKEY} ${message}"
}

logWarn() {
  message="${1}"
  tabs=${2}
  spaces=""
  if [ ! -z ${tabs} ]; then
    x=1
    while [ ${x} -le ${tabs} ]; do
      spaces="${spaces}--"
      x=$(($x + 1))
    done
  fi

  echo -e "${spaces} ${FIRE} ¡¡ WARNING !! '${message}'"
}
