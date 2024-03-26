# MyCAS

## cas-server

Generic CAS WAR overlay to exercise the latest versions of CAS. This overlay could be freely used as a starting template for local CAS war overlays.

# Gradlew

- ./gradlew tasks --> Muestra todas las tareas
- ./gradlew jibDockerBuild --> Genera el docker. No hace push

### Docker 

- ./docker-build-and-push.sh --> Genera el docker en el registry de Kubernetes y hace push al registry en localhost:3200
- ./docker-run.sh --> Lanza el contenedor generado con el script --> NO FUNCIONA AHORA MISMO

### Kubernetes

- ./docker-build-and-push.sh # Genera docker y lo sube al registry del cluster
- ./deploy-on-kubernetes # Despliega CAS en Kubernetes
- ./print-info.sh para mas instrucciones
