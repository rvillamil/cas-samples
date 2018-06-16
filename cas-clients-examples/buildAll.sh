#!/bin/bash 
#
# Copyright (C) Rodrigo Villamil Perez 2018
# Fichero: buildAll.sh
# Autor: Rodrigo
# Fecha: 27/05/18
#
echo "** Building 'cas-secured-app-one'"
cd cas-secured-app-one
mvn clean &&
mvn install
cd -

echo "** Building 'saml-secured-app-one'"
cd saml-secured-app-one
mvn clean &&
mvn install
cd -

echo "** Generating docker images..."
docker-compose stop
docker-compose rm
docker-compose build

