. .env

function main() {
    echo "Stopping database container. Name: ${container_name}"
    stopping_result=$(docker stop "${container_name}") || exit 1
    echo "Container has been stopped. Name: ${stopping_result}"
}

(main)
