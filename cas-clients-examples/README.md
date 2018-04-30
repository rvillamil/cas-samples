# Aplicaciones clientes de CAS

## Configuration

Para las pruebas en LOCAL, se require que tengas instalado el certificado en el almacen de claves de tu equipo, porque si no, la aplicacion cliente va a dar problemas con el protocolo SSL

```bash
sudo keytool -import -alias thekeystore -storepass changeit -file thekeystore.crt -keystore /etc/ssl/certs/java/cacerts
```
