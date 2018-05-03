#!/bin/bash 
#
# Copyright (C) Rodrigo Villamil Perez 2018
# Fichero: gen-cert.sh
# Fecha: 03/05/18
#

path_cacerts="/opt/java/jdk1.8.0_40_x86_64/jre/lib/security/cacerts"
path_cacerts_osx="/etc/ssl/certs/java/cacerts"

current_patch_certs=${path_cacerts}

echo "Deleting previous alias and current keystore .."
sudo rm -fv etc/cas/thekeystore
sudo rm -fv etc/cas/thekeystore.crt
sudo keytool -delete -alias thekeystore -keystore ${current_patch_certs} -storepass changeit

#DNAME="${DNAME:-CN=localhost,OU=localhost,O=localhost,L,=Madrid,ST=Madrid,C=ES}"
DNAME="${DNAME:-CN=localhost,OU=localhost,O=localhost,C=ES}"
echo "Generating keystore for CAS with DN ${DNAME}"
keytool -genkey -keyalg RSA -alias thekeystore -keystore etc/cas/thekeystore -storepass changeit -validity 360 -keysize 2048 -dname ${DNAME}
keytool -export -alias thekeystore -file etc/cas/thekeystore.crt -keystore etc/cas/thekeystore
sudo keytool -import -alias thekeystore -storepass changeit -file etc/cas/thekeystore.crt  -keystore ${current_patch_certs}

echo "Deploy keys in /etc/cas.."
./build.sh copy


  
