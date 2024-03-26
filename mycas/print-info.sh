#!/usr/bin/env bash
source functions-common.sh

function about_project() {
  echo ""
  echo "#########################################################"
  echo "#########################################################"
  echo "                ¡¡¡  R E A D M E   !!!                   "
  echo "#########################################################"
  echo "#########################################################"
  echo ""
  echo "---------------------------------------------------------"
  echo "                     /etc/hosts                          "
  echo "---------------------------------------------------------"
  echo ""
  echo "Incluir en el fichero /etc/hosts lo siguiente:"
  echo ""
  echo "127.0.0.1 mycas.localhost.local"
  echo ""  
  echo "---------------------------------------------------------"
  echo "                       C A S                             "
  echo "---------------------------------------------------------"
  echo ""
  echo "ATENCION!! Hacer port-forwarding de CAS !!!:"
  echo ""
  echo "kubectl port-forward svc/cas 9443:8443 -n ${MY_NAMESPACE}"
  echo ""
  echo ""
  echo "Luego visutar la web: https://mycas.localhost.local:9443/cas/login"
  echo "  - User: casuser"
  echo "  - Password: Mellon"
  echo ""
  echo "OIDC info: https://mycas.localhost.local:9443/cas/oidc/.well-known"
  echo ""
  echo ""
}

about_project
