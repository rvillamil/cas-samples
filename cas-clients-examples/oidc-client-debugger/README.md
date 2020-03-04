# oidc-client-debugger

Para testear con el cliente de 'https://oidcdebugger.com/', tendremos que
establecer estas opciones:

- Authorize URI (required): https://casdev.company.com:8443/cas/oidc/authorize
- Redirect URI (required) : https://oidcdebugger.com/debug  . Esta URL coincide con la que tenemos en el servicio del fichero OIDC*.json
- Client ID (required): client
- Scope: openid
- Response type (required):
   - code (Authorization code flow): Este es el que se usa cuando tenemos un backend que nos permite almacenar el 'secret'
        
        - Nos devuelve un "Authorization Code"  con el que lanzamos la petición a CAS junto con el secret para conseguir el accessToken. 
          Aqui la fortaleza es que el backend tiene el 'secret' custodiado y solo viaja en la peticion que solicita el accessToken:          
        - Ejemplo:

           ```        
           authorization_code="OC-2-QptIY3LvNvZWULXH7YmJ0M6BPvK0GoRt"
           request="https://casdev.company.com:8443/cas/oidc/accessToken?grant_type=authorization_code&code=${authorization_code}&client=client&client_secret=secret&redirect_uri=https://oidcdebugger.com/debug"

            curl --noproxy -v -X POST ${request} \
                -H 'Content-Type: application/x-www-form-urlencoded' \
                -H 'Host: casdev.company.com:8443'
                -H 'Accept: */*' \
                -H 'Accept-Encoding: gzip, deflate' \
                -H 'Cache-Control: no-cache' \
                -H 'Connection: keep-alive' \
                -H 'Content-Length: 0' \
                -H 'User-Agent: PostmanRuntime/7.17.1' \
                -H 'cache-control: no-cache'
           ```
           
   - token ( Implicit flow) : Este el que se usa cuando no usamos un backend por el medio. Nos devuelve el accessToken y no necesitamos un secret
        - Nos devuelve un accessToken, por ejemplo: AT-5-cYt9DFKzg4DNQHmRNKVxkAjB0omr-n3wb
   
   




