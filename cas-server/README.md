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
            <Certificate certificateKeystoreFile="/etc/cas/thekeystore"
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

En el directorio raiz tenemos un docker-compose con todo lo necesario.

Las imágenes se construyen utilizando el ultimo 'war' generado.

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
