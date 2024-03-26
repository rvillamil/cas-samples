#!/usr/bin/env bash

source functions-common.sh

docker stop "${CONTAINER_CAS_SERVER_NAME}" > /dev/null 2>&1
docker rm "${CONTAINER_CAS_SERVER_NAME}" > /dev/null 2>&1
docker run -p 8080:8080 -p 9443:9443 --name="${CONTAINER_CAS_SERVER_NAME}" "${MY_DOCKER_REGISTRY}/${IMAGE_TAG}"
#docker logs -f cas