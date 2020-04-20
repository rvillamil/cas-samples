# CAS Server y CAS Service management

## Preparación del entorno de desarrollo para  

### Opción 1 : La mas sencilla

- Ejecutar el comando 'buildAll.sh' que lo hace todo por ti

### Opción 2: Paso a paso

- Compilar el servidor CAS, que se encuentra en el directorio `cas-server/cas-server-overlay`, ejecutar los comandos

```bash
$./build package
$./build copy
```

Mas información en el [README](cas-server/cas-server-overlay/README.md) del proyecto

- Compilar el servidor de administración de CAS, que se encuentra en el directorio `cas-server/cas-services-management-overlay/`, ejecutar los comandos

```bash
$./build package
$./build copy
```

Mas información en el [README](cas-server/cas-services-management-overlay/README.md) del proyecto

## Desplegar en el servidor de aplicaciones: TOMCAT

Desplegando ambos 'war' en el servidor de aplicaciones. Tanto el CAS, como el CAS Management estarán disponibles en:

- `https://casdev.company.com:8443/cas`
- `https://casdev.company.com:8443/cas-management`

El fichero **server.xml**  en la versión de Tomcat 8.X sería:

```xml
<Connector SSLEnabled="true" maxThreads="150" port="8443"
       protocol="org.apache.coyote.http11.Http11NioProtocol">
        <SSLHostConfig>
            <Certificate certificateKeystoreFile="/etc/cas/caskeystore"
                   keystorePass="changeit" type="RSA"/>
        </SSLHostConfig>
    </Connector>
```

El Usuario y la clave por defecto sería

```bash
 - username: casuser
 - password: Mellon
```

**IMPORTANTE**: Cada vez que cambiemos algún fichero de condiguración de cas o cas-management, ejecutar el comando que copia a `/etc/cas` la configuración:

```bash
$./deploy-config.sh
```

## Soporte para docker

Se trata de un contenedor con la imagen de CAS desplegada en un tomcat con la configuracion

Para construir la imagen, entonces:

- Primero cargamos las utilidades

```bash
$source docker-tools.sh
```

- Compilamos. Este paso no es necesario si previamente has hecho un 'build-all.sh'
  
```bash
$docker_build
```

- Por ultimo, lanzamos el contenedor con la funcion que hemos cargado con las utilidades
  
```bash
$docker_run
```

### Publicar en docker-hub

- Hacer login en la cuenta de docker-hub

```bash
$docker login
```

- Publicar en docker-hub con:

```bash
$docker_push
```

### Ejecutar una shell en el contenedor

```bash
$docker exec -it cas-server-mngt /bin/sh
```
