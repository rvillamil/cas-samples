# Aplicaciones clientes de CAS

Para las pruebas en LOCAL, se require que tengas instalado el certificado en el almacen de claves de tu equipo, porque si no, la aplicacion cliente va a dar problemas con el protocolo SSL

```sh
sudo keytool -import -alias thekeystore -storepass changeit -file thekeystore.crt -keystore /etc/ssl/certs/java/cacerts
```
