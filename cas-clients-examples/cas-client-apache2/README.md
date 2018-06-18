# PHP Docker with SSL and MOD_AUTH_CAS

#### A simple docker configuration to use PHP with HTTPS for development

This is a simple Dockerfile that uses the PHP-Apache image and creates a self
signed certificate to enable the default SSL. This is suitable for development
and testing, not for production.

## Usage

Download the repository and use the docker compose to build and run the
container:

###### Build container
```
docker-compose build

###### Build container with PROXY support

docker-compose build --build-arg proxy=http://14.56.78.90:8222 cas-client-apache2
```
###### Run container
```
docker-compose up
```

###### Remove container
```
docker-compose down
```

###### Run bas terminal

docker exec -i -t cnt-cas-client-apache2 /bin/bash

After you run the command above you should be able to access:

`https://casclient1.company.com/casclient`


