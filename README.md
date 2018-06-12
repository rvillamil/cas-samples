# CAS Server, CAS Services Management y clientes ejemplos

Con este proyecto, pretendo explorar las posibilidades de CAS, entender el producto y sus posibilidades.

Me he apoyado fuertemente en la siguiente documentación:

* [The new School](https://dacurry-tns.github.io/deploying-apereo-cas/building_server_configure-server-properties.html)
* [Baeldung](http://www.baeldung.com/spring-security-cas-sso)

## [CAS Server y CAS Service Management](cas-server/README.md)

[Servidor CAS](cas-server/cas-server-overlay/README.md) en el directorio `cas-server/cas-overlay-server` con las siguentes funcionalidades:

- Configuración del servidor CAS tipo 'Standalone', donde toda la configuracion se encuentra externalizada, en el directorio /etc/cas
- Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry
- Protocolo CAS 
- Protoclo SAML

[Administrador de servicios del servidor de CAS](cas-server/cas-services-management-overlay/README.md) en el directorio `cas-server/cas-services-management-overlay` con las siguientes funcionalidades:

- Configuración del servidor con el perfil 'Standalone'. Toda la configuracion externalizada, se encuentra en el directorio `/etc/cas`
- Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry . Importante que sea el mismo que el Servidor de Cas

## [Clientes CAS, SAML2, OAuhy para pruebas](cas-clients-examples/README.md)

Tenemos una pequeña infraestructura de clientes, bien desarrollados pos nosotros o bien de otros desarrolladores, que nos sirven para probar las funcionalidades de CAS.


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

- Instalar y configurar el [CAS Server y CAS Service Management](cas-server/README.md)

- Instalar y configurar los [clientes](cas-clients-examples/README.md) para probar el CAS Server