#!/bin/bash 
#
# Copyright (C) Rodrigo Villamil Perez 2018
# Fichero: buildAll.sh
# Autor: Rodrigo
# Fecha: 27/05/18
#
echo "** Creating deploy dir /etc/cas"
mkdir -p /etc/cas

echo "** Clean deploy dir /etc/cas"
sudo rm -rf /etc/cas/*

echo "** Building 'cas-server-overlay'"
cd cas-server-overlay
./build.sh clean &&
./build.sh package &&
./build.sh copy
cd -

echo "** Building 'cas-services-management-overlay'"
cd cas-services-management-overlay
./build.sh clean &&
./build.sh package &&
./build.sh copy 
cd -

echo "** Generando contenedor Docker .."
source docker-tools.sh
docker_build