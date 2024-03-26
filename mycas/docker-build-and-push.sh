#!/usr/bin/env bash

source functions-common.sh

logInfo "Docker registry set on '${MY_DOCKER_REGISTRY}'" 1

# Generamos los certificados
cd ${CERTS_BASE_PATH}
./generate-cert.sh
cd - > /dev/null 2>&1

logInfo "Copying my certificate from '${MYCERT_PATH}' directory" 1
cp ${MYCERT_PATH} ${BASE_PATH}/etc/cas/

logInfo "Copying the keystore from '${MYKEYSTORE_PATH}' directory" 1
cp ${MYKEYSTORE_PATH} ${BASE_PATH}/etc/cas/
  
logInfo "Building CAS docker image tagged as '${IMAGE_TAG}'" 1
docker rmi "${MY_DOCKER_REGISTRY}/${IMAGE_TAG}" >/dev/null 2>&1
cd ${BASE_PATH}
docker build --tag="${MY_DOCKER_REGISTRY}/${IMAGE_TAG}" -f Dockerfile . \
  && logInfo "Built CAS image successfully tagged as '${MY_DOCKER_REGISTRY}/${IMAGE_TAG}'"
cd - > /dev/null 2>&1

# Push to registry
logInfo "Pushing CAS docker image tagged as '${IMAGE_TAG}' to registry on '${MY_DOCKER_REGISTRY}'" 1
docker push "${MY_DOCKER_REGISTRY}/${IMAGE_TAG}"