. .env

function main() {
    echo "Stopping database container. Name: ${DOCKER_POSTGRE_CONTAINER_NAME}"
    stopping_result=$(docker stop "${DOCKER_POSTGRE_CONTAINER_NAME}") || exit 1
    echo "Container has been stopped. Name: ${stopping_result}"
}

(main)
