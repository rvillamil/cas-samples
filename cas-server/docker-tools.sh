# 
# LOCAL
#
export DOCKER_REGISTRY_URI="localhost:32000/arquitectura/samples/cas"
export IMAGE_VERSION="latest"

# 
# Desarrollo
#
#export DOCKER_REGISTRY_URI=""
#export IMAGE_VERSION="1.0.0-SNAPSHOT"


export IMAGE_NAME="cas-server-mngt"
export CONTAINER_NAME="cas-server-mngt"
export INTERNAL_PORT="8443" # El del Tomcat
export EXTERNAL_PORT="8443" # El que exporta docker

echo "Cargando la configuracion siguiente: "
echo "    - Docker Registry ...................: '${DOCKER_REGISTRY_URI}'"
echo "    - Nombre de la imagen ...............: '${IMAGE_NAME}'"
echo "    - Versión de la imagen ..............: '${IMAGE_VERSION}'"
echo "    - Nombre del contenedor .............: '${CONTAINER_NAME}'"
echo "    - Puerto interno de la aplicación ${INTERNAL_PORT} y puerto externo docker ${EXTERNAL_PORT}"
echo ""
echo "Ahora tienes disponibles estos comandos:"
echo ""
echo "docker_build ..: Compila el proyecto y genera la imagen docker en local"
echo "$ "
echo "docker_push ...: Sube la imagen a un docker registry que se encuentre en '${DOCKER_REGISTRY_URI}'"
echo "docker_run ....: Lanza un contenedor de nombre '${CONTAINER_NAME}'. Si ya existe uno con ese nombre, lo borra"
echo "    - Abir navegador en: http://127.0.0.1:${EXTERNAL_PORT}/actuator"
echo "    - Para obtener las URLs de actuator: curl localhost:${EXTERNAL_PORT}/actuator  | jq"


tool_dir=`pwd` # Para ejecutar el script fuera de este directorio

function docker_build() {
    cd ${tool_dir}  > /dev/null 2>&1
    #./buildAll.sh
    docker build -t ${DOCKER_REGISTRY_URI}/${IMAGE_NAME}:${IMAGE_VERSION} -f Dockerfile .
    cd -  > /dev/null 2>&1
}

function docker_push() {
    cd ${tool_dir}  > /dev/null 2>&1
    docker push ${DOCKER_REGISTRY_URI}/${IMAGE_NAME}:${IMAGE_VERSION}
    cd -  > /dev/null 2>&1
}

function docker_run() {
    cd ${tool_dir}  > /dev/null 2>&1
    docker rm ${CONTAINER_NAME} > /dev/null 2>&1
    docker run --name ${CONTAINER_NAME} -p ${EXTERNAL_PORT}:${INTERNAL_PORT} ${DOCKER_REGISTRY_URI}/${IMAGE_NAME}:${IMAGE_VERSION}
    cd -  > /dev/null 2>&1
}
