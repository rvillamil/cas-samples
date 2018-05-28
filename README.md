# CAS Server, CAS Services Management and Clients Samples

Con este proyecto, pretendo explorar las posibilidades de CAS, entender el producto y sus posibilidades.

En este momento, tenemos implementado lo siguiente:

[Servidor CAS](cas-server/cas-server-overlay/README.md) en el directorio `cas-server/cas-overlay-server` con las siguentes funcionalidades:

- Configuración del servidor con el perfil 'Standalone'. Toda la configuracion externalizada, se encuentra en el directorio /etc/cas
- Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry
- Protocolo CAS (Por defecto)

[Administrador de servicios del sercidor de CAS](cas-server/cas-services-management-overlay/README.md) en el directorio `cas-server/cas-services-management-overlay` con las siguentes funcionalidades:

- Configuración del servidor con el perfil 'Standalone'. Toda la configuracion externalizada, se encuentra en el directorio `/etc/cas`
- Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry . Importante que sea el mismo que el Servidor de Cas

[Aplicacion cliente CAS](cas-clients-examples/README.md) en `cas-clients-examples/cas-secured-app-one`, implementada utilizando el soporte de springboot. Emplea el protocolo CAS para conectarse con el servidor CAS.

Me he apoyado en la siguiente documentacion :
* [Baeldung](http://www.baeldung.com/spring-security-cas-sso)
* [The new School] (https://dacurry-tns.github.io/deploying-apereo-cas/building_server_configure-server-properties.html)


## Instalacion de la parte servidora
0 - Incluir en el fichero de /etc/hosts lo siguiente:

127.0.0.1 casdev.company.com casdev.company.com casclient1.company.com casclient2.company.com samlclient1.company.com samlclient2.company.com

Es mas facil de seguir con nombres de dominio

1 - Regenerar los certificados, utilizando la password 'changeit' para las preguntas que te solicite:

```bash
$gen-cert.sh
```

NOTA: Este scripr incluye los certificados en el almacen de claves de tu equipo, porque si no, la aplicacion cliente va a dar problemas con el protocolo SSL


2 - Config application server with defaults pors 8080 y 8443 and ensure the keystore is loaded up with keys and certificates of the server

e.g. TOMCAT 8.5 server.xml in Tomcat 8.X:

```xml
<Connector SSLEnabled="true" maxThreads="150" port="8443"
       protocol="org.apache.coyote.http11.Http11NioProtocol">
        <SSLHostConfig>
            <Certificate certificateKeystoreFile="/etc/cas/thekeystore"
                   keystorePass="changeit" type="RSA"/>
        </SSLHostConfig>
    </Connector>
```

3 - Crear el directorio /etc/cas

4 - En el directorio `cas-server/cas-server-overlay`, ejecutar los comandos

```bash
$./build package
$./build copy
```

5 - En el directorio `cas-server/cas-services-management-overlay/`, ejecutar los comandos

```bash
$./build package
$./build copy


5 - On a successful deployment via the following methods:

- CAS will be available at:

  - `http://localhost:8080/cas`
  - `https://localhost:8443/cas`

- CAS Management will be available at:

  - `http://localhost:8080/cas-management`
  - `https://localhost:8443/cas-management`


6 - Default user and password:

 - username: casuser
 - password: Mellon

Importante, Cada vez que cambiemos algún fichero de condiguración de cas o cas-management, ejecutar el comando que copia a `/etc/cas` la configuración:

```bash
$./deploy-config.sh
```

# Aplicaciones cliente

## cas-secured-app-one

Cliente que usa el protocolo CAS: Aplicacion Spring Boot, que se ejecuta como una aplicacion springboot standalone

- `http://localhost:9001`

## saml-secured-app-one

Service Provider que usa el protocolo SAML: Aplicacion Spring Boot, que se ejecuta como una aplicacion springboot standalone


- `http://localhost:9002`

# Soporte para docker

En el directorio raiz tenemos un docker-compose con todo lo necesario. Se equiere que antes se hayan compilado los servicios detallados en el docker compose,
para generar las imagenes. Una vez compilados los proyectos, ejecutamos:

## Construir todos los contenedores

```bash
$ docker-compose build
```

## Arrancar todos los contenedores

```bash
$ docker-compose up
```


## Arrancar un contenedor especifico: Ejemplo cas-server-overlay

```bash
$ docker-compose up cas-server-overlay
```


## Ejecutar una shell en el contenedor

```bash
$ docker exec -it cnt-cas-server-overlay /bin/sh
```


