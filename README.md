# CAS Server: Standalone Configuration

Con este proyecto, pretendo explorar las posibilidades de CAS, entender el producto y sus posibilidades.

En este momento, tenemos implementado lo siguiente:

- [Servidor CAS](cas-server/cas-overlay-server/README.md) en el directorio 'cas-server/cas-overlay-server' con las siguentes funcionalidades:
  - Protocolo CAS
  - Configuración del servidor con el perfil 'Standalone'
  - Registro de aplicaciones clientes CAS basadas en Json. JSON Service Registry
- [Aplicacion cliente CAS](cas-clients-examples/README.md) en 'cas-clients-examples/cas-secured-app-one', implementada utilizando el soporte de springboot. Emplea el protocolo CAS para conectarse con el servidor CAS

Me he apoyado en la documentacion y el código de [Baeldung](http://www.baeldung.com/spring-security-cas-sso)

Mis dieses para Baeldung ;)
