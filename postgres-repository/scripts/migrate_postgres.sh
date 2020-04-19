function run_migrations() {
    absolute_path_to_scripts="${1}"
    MSYS_NO_PATHCONV=1 docker run --rm \
            --network "host" \
            -v "${absolute_path_to_scripts}:/flyway/sql" flyway/flyway \
            -user="${CLEANING_TIME_DATABASE_USERNAME}" \
            -password="${CLEANING_TIME_DATABASE_PASSWORD}" \
            -url="${CLEANING_TIME_DATABASE_URL}" \
            migrate
}

function get_path_to_sql() {
    local relative_path_to_scripts="postgres-repository/src/main/resources/db/migration"
    local absolute_path_to_scripts="$(pwd)/${relative_path_to_scripts}"
    echo "${absolute_path_to_scripts}"
}

function main() {
    path_to_sql=$(get_path_to_sql)
    run_migrations "${path_to_sql}"
}

(main)
