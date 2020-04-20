#!/bin/bash 
#
# Copyright (C) Rodrigo Villamil Perez 2018
# Fichero: deploy-config.sh
# Autor: Rodrigo
# Fecha: 27/05/18
#
#
echo "** Removing old config from /etc/cas/"
rm -rf /etc/cas/*

cd cas-server-overlay
./build.sh copy
cd -

cd cas-services-management-overlay
./build.sh copy 
cd -

./gen-cert.sh

echo "** Config deployed in /etc/cas **"
