#!/bin/bash 
#
# Copyright (C) Rodrigo Villamil Perez 2018
# Fichero: gen-cert.sh
# Fecha: 03/05/18
#

echo "Deleting previous alias and current keystore .."
sudo rm -fv etc/cas/thekeystore
sudo rm -fv etc/cas/thekeystore.crt
sudo keytool -delete -alias thekeystore -keystore /etc/ssl/certs/java/cacerts -storepass changeit

#DNAME="${DNAME:-CN=localhost,OU=localhost,O=localhost,L,=Madrid,ST=Madrid,C=ES}"
DNAME="${DNAME:-CN=localhost,OU=localhost,O=localhost,C=ES}"
echo "Generating keystore for CAS with DN ${DNAME}"
keytool -genkey -keyalg RSA -alias thekeystore -keystore etc/cas/thekeystore -storepass changeit -validity 360 -keysize 2048 -dname ${DNAME}
keytool -export -alias thekeystore -file etc/cas/thekeystore.crt -keystore etc/cas/thekeystore
sudo keytool -import -alias thekeystore -storepass changeit -file etc/cas/thekeystore.crt  -keystore /etc/ssl/certs/java/cacerts

echo "Deploy keys in /etc/cas.."
./build.sh copy


  
