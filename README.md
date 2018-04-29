# CAS Server: Standalone Configuration

Con este proyecto, pretendo explorar las posibilidades de CAS, entender el producto y sus posibilidades.

He montado un CAS Server, con la configuracion standalone para su despliegue en su servidor de aplicaciones. Ademas trataré de incoporar clientes CAS que utilicen los distintos protocolos soportados:

- CAS
- SAML
- OAuth

Me he apoyado en la documentacion y el código de [Baeldung](http://www.baeldung.com/spring-security-cas-sso)

Mis dieses para Baeldung

# Modificaciones sobre la plantilla original

Me he descargado y modificado la plantilla [CAS Overlay Template](https://github.com/apereo/cas-overlay-template) modificando el fichero
'build.sh', remplazando el comando 'mvnw' por el 'mvn' para evitar problemas con algún proxy

He generado la clave para el servidor de aplicaciones, de la siguiente forma:

```sh
$keytool -genkey -keyalg RSA -alias thekeystore -keystore thekeystore -storepass changeit -validity 360 -keysize 2048
```

"It’s important to use localhost when prompted for a first and last name, organization name and even organization unit. Failure to do this may lead an to error during SSL Handshake. Other fields such as city, state and country can be set as appropriate."

```sh
$keytool -export -alias thekeystore -file thekeystore.crt -keystore thekeystore
```

Por ultimo ..

```sh
$sudo keytool -import -alias thekeystore -storepass changeit -file thekeystore.crt -keystore /etc/ssl/certs/java/cacerts
```

# Despliegue del CAS Server en modo 'Standalone'

Hemos elegido el perfil 'Standalone' siguiendo la documentacion de [Aepero](https://apereo.github.io/cas/development/installation/Configuration-Server-Management.html).

Este despliegue, usa la configuracion del directorio '/etc/cas' y por ello hay que crear el enlace siguiente:

```sh
$sudo ln -s [path_this_project]/etc/cas /etc/cas
```

"When this option is turned on, CAS by default will attempt to
 locate settings and properties inside a given directory indicated under
 the setting name cas.standalone.configurationDirectory
 and otherwise falls back to using /etc/cas/config as the configuration directory"

## Configurar el 'Cas Server' integrado con eclipse y Tomcat 8.X

Se trata de instalar en el eclipse el Tomcat 8.5 y cambiar el 'server.xml', descomentando las líneas que se refieren al HTTPS de esta forma:

```xml
<Connector SSLEnabled="true" maxThreads="150" port="8443"
       protocol="org.apache.coyote.http11.Http11NioProtocol">
        <SSLHostConfig>
            <Certificate certificateKeystoreFile="/etc/cas/thekeystore"
                   keystorePass="changeit" type="RSA"/>
        </SSLHostConfig>
    </Connector>
```

## Iniciar CAS desde la consola: Executable WAR

Para iniciar el CAS Server desde una terminal, utilizando la configutracion de '/etc/cas', empaquetamos el WAR así:

```sh
$sh build.sh package
```

...Lanzamos la aplicación con un tomcat embebido así ..

```sh
$sh build.sh run
```

# URL de acceso al CAS Server desplegado

-  https://localhost:8443/cas/login , o bien,  http://localhost:8080/cas/login

- Usuario por defecto
  - username: casuser
  - password: Mellon

# Aplicaciones clientes de CAS

Para las pruebas en LOCAL, se require que tengas instalado el certificado en el almacen de claves de tu equipo, porque si no, la aplicacion cliente va a dar problemas con el protocolo SSL

```sh
sudo keytool -import -alias thekeystore -storepass changeit -file thekeystore.crt -keystore /etc/ssl/certs/java/cacerts
```
