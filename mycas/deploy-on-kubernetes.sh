#!/usr/bin/env bash
source functions-common.sh

if [ -z "${MY_NAMESPACE}" ]; then
    MY_NAMESPACE="default"
fi
logInfo "Deleting CAS deployment from namespace '${MY_NAMESPACE}'"
kubectl delete -f ${BASE_PATH}/deployment.yaml -n ${MY_NAMESPACE}
sleep 2
logInfo "Creating CAS deployment from namespace '${MY_NAMESPACE}'"
kubectl apply -f ${BASE_PATH}/deployment.yaml -n ${MY_NAMESPACE}

source print-info.sh
