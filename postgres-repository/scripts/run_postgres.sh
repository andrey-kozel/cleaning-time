. .env

function run_postgres_container() {
    echo "Running postgres container.
            Name: ${DOCKER_POSTGRE_CONTAINER_NAME}.
            Volume: postgres_volume. Postres version: ${DOCKER_POSTGRES_VERSION}"
    container_id=$(docker run \
        --rm \
        --name "${DOCKER_POSTGRE_CONTAINER_NAME}" \
        -e POSTGRES_PASSWORD="${CLEANING_TIME_DATABASE_PASSWORD}" \
        -d \
        -p 5432:5432 \
        -v "${DOCKER_POSTGRES_VOLUME_NAME}":/var/lib/postgresql/data \
        "${DOCKER_POSTGRES_VERSION}")
    echo "Container is running. Id: ${container_id}"
}

function create_volume_if_not_exists() {
    local volume_info="${1}"
    echo "Check for persistant volume. Name: ${DOCKER_POSTGRES_VOLUME_NAME}"
    if [[ -z "${volume_info}" ]]; then
        echo "Volume not exists. Creating volume. Name: ${DOCKER_POSTGRES_VOLUME_NAME}"
        docker volume create \
            --name "${DOCKER_POSTGRES_VOLUME_NAME}" \
            -d local
    else
        echo "Volume exists. Nam: ${DOCKER_POSTGRES_VOLUME_NAME}"
    fi
}

function get_volume() {
    docker volume ls | grep "${DOCKER_POSTGRES_VOLUME_NAME}"
}

function main() {
    local volume=$(get_volume)
    create_volume_if_not_exists "${volume}"
    run_postgres_container
}

(main)
