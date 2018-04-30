# CAS Server: Standalone Configuration

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

Me he apoyado en la documentacion y el código de [Baeldung](http://www.baeldung.com/spring-security-cas-sso)

Mis dieses para Baeldung ;)

## Instalacion 

1 - Para las pruebas en LOCAL, se require que tengas instalado el certificado en el almacen de claves de tu equipo, porque si no, la aplicacion cliente va a dar problemas con el protocolo SSL

```bash
$sudo keytool -import -alias thekeystore -storepass changeit -file thekeystore.crt -keystore /etc/ssl/certs/java/cacerts
```

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

4 - Ejecutar el comando ./build.sh package

5 - On a successful deployment via the following methods, CAS will be available at:

  - `http://localhost:8080/cas`
  - `https://localhost:8443/cas`

6 - Default user and password:

 - username: casuser
 - password: Mellon

Importante, Cada vez que cambiemos algún fichero de condiguración, ejecutar el comando que copia a `/etc/cas``

```bash
$./build copy
```
