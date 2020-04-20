#!/bin/bash
#
# Copyright (C) Rodrigo Villamil Perez 2019
# Fichero: get_tokens.sh
# Autor: rvp001es
# Fecha: 26/09/19
#
#

#code="OC-1-bsbQAi-y3ehhSMAkC2-8iSzDDPcfrb5o"

[ -z "${code}" ] && echo "Exporta la variable de entorno 'code' con el Authorization Code devuelto" && exit 1

#code="OC-2-QptIY3LvNvZWULXH7YmJ0M6BPvK0GoRt"
request="https://casdev.company.com:8443/cas/oidc/accessToken?grant_type=authorization_code&code=${code}&client=client&client_secret=secret&redirect_uri=https://oidcdebugger.com/debug"

curl --noproxy -v -X POST ${request} \
     -H 'Content-Type: application/x-www-form-urlencoded' \
     -H 'Host: casdev.company.com:8443'
 # -H 'Accept: */*' \
#  -H 'Accept-Encoding: gzip, deflate' \
#  -H 'Cache-Control: no-cache' \
#  -H 'Connection: keep-alive' \
#  -H 'Content-Length: 0' \

#  -H 'User-Agent: PostmanRuntime/7.17.1' \
#  -H 'cache-control: no-cache'



#curl -v -X POST \
#-H "Content-type:application/x-www-form-urlencoded" \
#"https://{yourOktaDomain}/oauth2/default/v1/token" \
#-d "client_id={client_id}&client_secret={client_secret}&grant_type=authorization_code&redirect_uri={redirect_uri}&code={code}"
