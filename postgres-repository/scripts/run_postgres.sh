. .env

function run_postgres_container() {
    echo "Running postgres container. Name: ${container_name}. Volume: postgres_volume. Postres version: ${postgres_version}"
    container_id=$(docker run \
        --rm \
        --name "${container_name}" \
        -e POSTGRES_PASSWORD="${postgres_password}" \
        -d \
        -p 5432:5432 \
        -v "${postgres_volume}":/var/lib/postgresql/data \
        "${postgres_version}")
    echo "Container is running. Id: ${container_id}"
}

function create_volume_if_not_exists() {
    local volume_info="${1}"
    echo "Check for persistant volume. Name: ${postgres_volume}"
    if [[ -z "${volume_info}" ]]; then
        echo "Volume not exists. Creating volume. Name: ${postgres_volume}"
        docker volume create \
            --name "${postgres_volume}" \
            -d local
    else
        echo "Volume exists. Nam: ${postgres_volume}"
    fi
}

function get_volume() {
    docker volume ls | grep "${postgres_volume}"
}

function main() {
    local volume=$(get_volume)
    create_volume_if_not_exists "${volume}"
    run_postgres_container
}

(main)
