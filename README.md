# CAS Server, CAS Services Management y clientes ejemplos

Con este proyecto, pretendo explorar las posibilidades de CAS, entender el producto y sus posibilidades.

Me he apoyado fuertemente en la siguiente documentación:

* [The new School](https://dacurry-tns.github.io/deploying-apereo-cas/building_server_configure-server-properties.html)
* [Baeldung](http://www.baeldung.com/spring-security-cas-sso)

## CAS Server y CAS Service Management

El [Servidor CAS](cas-server/cas-server-overlay/README.md) propiamente dicho, se encuentra en el directorio `cas-server/cas-overlay-server` con las siguientes funcionalidades:

* Configuración del servidor CAS tipo 'Standalone', donde toda la configuración se encuentra "externalizada", en el directorio /etc/cas

* Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry

* Protocolo CAS

* Protoclo SAML
  
* Protocolo OpenID Connect

[Administrador de servicios CAS](cas-server/cas-services-management-overlay/README.md) en el directorio `cas-server/cas-services-management-overlay` con las siguientes funcionalidades:

* Configuración del servidor con el perfil 'Standalone'. Toda la configuración "externalizada", se encuentra en el directorio `/etc/cas`
  
* Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry . Importante que sea el mismo que el Servidor de Cas

## Clientes de pruebas: CAS, OAuth2, SAML, OpenID Connect

Tenemos una pequeña [colección de clientes](cas-clients-examples/README.md), bien desarrollados por nosotros o bien clonados de otros desarrolladores, que nos sirven para probar las funcionalidades de CAS.

## Preparación del entorno de desarrollo

Para montar el entorno de desarrollo adecuadamente, hay que seguir la siguiente receta:

* En lugar de localhost, utilizamos nombres de dominio, ya que es mas fácil de seguir. Para ello, incluir en el fichero de /etc/hosts lo siguiente:

```bash
127.0.0.1 casdev.company.co
* Generar/regenerar los certificados, utilizando la password 'changeit', donde se solicite, lanzando el script:

```bash
cd cas-server
$gen-cert.sh
```

Este script incluye los certificados en el almacén de claves de tu equipo, porque si no, la aplicación cliente va a dar problemas con el protocolo SSL

* Seguir la guía para instalar y configurar el [CAS Server y/o CAS Service Management](cas-server/README.md)

* Recomendable instalar y configurar los [clientes](cas-clients-examples/README.md) para probar el CAS Server
