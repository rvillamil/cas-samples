# Aplicaciones clientes de CAS

## Clientes CAS

### [cas-secured-app-one](cas-secured-app-one/README.md) 

Aplicación implementada utilizando el soporte de springboot. Emplea el protocolo CAS para conectarse con el servidor CAS.

### [cas-client-apache2](cas-client-apache2/README.md)

Aplicación desplegada en un apache, que tiene configurado el soporte de `mod_auth_cas`, que nos permite conectar con el servidor CAS

## Clientes SAML2

### [saml-secured-app-one](saml-secured-app-one/README.md) 

Aplicación implementada utilizando el soporte de springboot. Es un SAML2 'service provider' que redirecciona al servidor CAS que hace de 'identity provider'
 
### [saml-client-shibboleth-sp](saml-client-shibboleth-sp/README.md) 

Aplicación desplegada en un apache, que hace de SAML2 'service provider' gracias al soporte de 'shibboleth2'

## Preparacion del entorno de desarrollo

- Incluir en el fichero de /etc/hosts lo siguiente:

```bash
127.0.0.1 casdev.company.com casclient1.company.com casclient2.company.com samlclient1.company.com samlclient2.company.com
```

## Soporte para docker

En el directorio raiz tenemos un docker-compose con todo lo necesario. 

Se requiere que antes se hayan compilado los servicios detallados en el docker compose, para generar las imagenes. 

Una vez compilados los proyectos, ejecutamos:

### Construir todos los contenedores

```bash
$ docker-compose build
```

### Arrancar todos los contenedores

```bash
$ docker-compose up
```

### Arrancar un contenedor especifico: 

Ejemplo `saml-secured-app-one`

```bash
$ docker-compose up saml-secured-app-one
```

### Ejecutar una shell en el contenedor

```bash
$ docker exec -it cnt-saml-secured-app-one /bin/sh
```

