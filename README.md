# CAS Server, CAS Services Management y clientes ejemplos

Con este proyecto, pretendo explorar las posibilidades de CAS, entender el producto y sus posibilidades.

Me he apoyado fuertemente en la siguiente documentación:

* [The new School](https://dacurry-tns.github.io/deploying-apereo-cas/building_server_configure-server-properties.html)
* [Baeldung](http://www.baeldung.com/spring-security-cas-sso)

## CAS Server y CAS Service Management

[Servidor CAS](cas-server/cas-server-overlay/README.md) en el directorio `cas-server/cas-overlay-server` con las siguentes funcionalidades:

- Configuración del servidor CAS tipo 'Standalone', donde toda la configuracion se encuentra externalizada, en el directorio /etc/cas
- Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry
- Protocolo CAS 
- Protoclo SAML

[Administrador de servicios del sercidor de CAS](cas-server/cas-services-management-overlay/README.md) en el directorio `cas-server/cas-services-management-overlay` con las siguentes funcionalidades:

- Configuración del servidor con el perfil 'Standalone'. Toda la configuracion externalizada, se encuentra en el directorio `/etc/cas`
- Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry . Importante que sea el mismo que el Servidor de Cas

## Clientes CAS, SAML2, OAuhy para pruebas

Tenemos una pequeña infraestructura de clientes, bien desarrollados pos nosotros o bien de otros desarrolladores, que nos sirven para probar las funcionalidades de CAS.

Mas [información](cas-clients-examples/README.md) sobre los clientes y su instalación.


## Preparacion del entorno de desarrollo

- En lugar de localhost, utilizamos nombres de dominio, ya que es mas facil de seguir. Para ello, incluir en el fichero de /etc/hosts lo siguiente:

```bash
127.0.0.1 casdev.company.com 
```

- Generar/regenerar los certificados, utilizando la password 'changeit' (en lasp preguntas que te lo solicite):

```bash
$gen-cert.sh
```

NOTA: Este script incluye los certificados en el almacen de claves de tu equipo, porque si no, la aplicacion cliente va a dar problemas con el protocolo SSL

- Configurar el servidor de aplicaciones para que cargue los certificados y escuche en los puertos, 8080 y 8443.

ej. TOMCAT 8.5 server.xml en Tomcat 8.X:

```xml
<Connector SSLEnabled="true" maxThreads="150" port="8443"
       protocol="org.apache.coyote.http11.Http11NioProtocol">
        <SSLHostConfig>
            <Certificate certificateKeystoreFile="/etc/cas/thekeystore"
                   keystorePass="changeit" type="RSA"/>
        </SSLHostConfig>
    </Connector>
```

- Crear el directorio /etc/cas

- Compilar el servidor CAS, que se encuentra en el directorio `cas-server/cas-server-overlay`, ejecutar los comandos

```bash
$./build package
$./build copy
```

- Compilar el servidor de administración de CAS, que se encuentra en el directorio `cas-server/cas-services-management-overlay/`, ejecutar los comandos

```bash
$./build package
$./build copy
```

- Desplegar ambos 'war' en el servidor de aplicaciones. CAS y CAS Management estarán disponibles en:

  - `https://casdev.company.com:8443/cas`
  - `https://casdev.company.com:8443/cas-management`

- Usuario y claves por defecto:
```bash
 - username: casuser
 - password: Mellon
```

- IMPORTANTE: Cada vez que cambiemos algún fichero de condiguración de cas o cas-management, ejecutar el comando que copia a `/etc/cas` la configuración:

```bash
$./deploy-config.sh
```

## Soporte para docker

En el directorio raiz tenemos un docker-compose con todo lo necesario. 
Se requiere que antes se hayan compilado los servicios detallados (servidores o [clientes](cas-clients-examples/README.md) ) en el docker compose, para 	generar las imagenes. Una vez compilados los proyectos, ejecutamos:

### Construir todos los contenedores

```bash
$ docker-compose build
```

### Arrancar todos los contenedores

```bash
$ docker-compose up
```

### Arrancar un contenedor especifico: 

Ejemplo `cas-server-overlay`

```bash
$ docker-compose up cas-server-overlay
```

### Ejecutar una shell en el contenedor

```bash
$ docker exec -it cnt-cas-server-overlay /bin/sh
```


