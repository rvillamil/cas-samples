#!/bin/bash
#
# Copyright (C) Rodrigo Villamil Perez 2016
# Fichero: tools.sh
# Autor: Rodrigo Villamil Perez
# Fecha: 14/05/2018
#
# Description: Utilities for docker maintenance
#

# Images and containers name
APACHE_CAS_IMAGE="img-apache-cas-client:latest"
APACHE_CAS_CONTAINER="cnt-apache-cas-client"
APACHE_PORT=8090

# ---------------------------- Docker utilities --------------------------------
message()
{
	echo "*** $1"
}
build()
{
    message "Building image'${APACHE_CAS_IMAGE}' ..."
    docker build -t ${APACHE_CAS_IMAGE} .
}

run()
{
    message "Run APACHE container '${APACHE_CAS_CONTAINER}'on port ${APACHE_PORT}. HTML content from 'html' dir"    
	docker run -dit --name ${APACHE_CAS_CONTAINER} -p ${APACHE_PORT}:80 -v "${PWD}"/html:/var/www/html ${APACHE_CAS_IMAGE}
}

stop()
{
    message "Stop APACHE container '${APACHE_CAS_CONTAINER}'"    
	docker stop ${APACHE_CAS_CONTAINER}
}

remove_container()
{
	stop
    message "Removing APACHE container '${APACHE_CAS_CONTAINER}'"    
	docker rm ${APACHE_CAS_CONTAINER}
}

bash_in_container()
{    
    message "Running bash over '${1}' container"
    docker exec -i -t ${1} /bin/bash
}


# ----------------------------------------------------------------------
while :
do
    clear
    cat<<EOF
 ======================================
    	C.I Tool Stack Utilities
 ======================================

	(0) Build '${APACHE_CAS_IMAGE}'
	(1) Run '${APACHE_CAS_CONTAINER}' container
	(2) Stop '${APACHE_CAS_CONTAINER}' container
	(3) Remove '${APACHE_CAS_CONTAINER}' container
	(4) Open terminal in '${APACHE_CAS_CONTAINER}' container
	     
	(q) Quit
  --------------------------------
EOF
    echo -n "Choose one option: "
    read -n1 -s
    echo ""
    case "$REPLY" in
	"0")  build; exit 0 ;;
	"1")  run; exit 0 ;;
	"2")  stop; exit 0 ;;   
	"3")  remove_container; exit 0 ;;	
	"4")  bash_in_container ${APACHE_CAS_CONTAINER}; exit 0 ;;
	
	"q"|"Q")  exit 0 ;;
	* )  echo "Invalid option !!" ;;
    esac
    echo ""
    echo -n "Press enter key ..."
    read enter_key
done

