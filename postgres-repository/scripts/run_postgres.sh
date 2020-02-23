docker run \
    --rm \
    --name postgres \
    -e POSTGRES_PASSWORD=docker \
    -d \
    -p 5432:5432 \
    postgres:12
docker exec -it postgres psql -U postgres