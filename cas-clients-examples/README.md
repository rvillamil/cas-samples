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