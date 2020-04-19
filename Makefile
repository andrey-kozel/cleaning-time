#!make
include .env
export $(shell sed 's/=.*//' .env)

run-postgres:
	@bash ./postgres-repository/scripts/run_postgres.sh

stop-postgres:
	@bash ./postgres-repository/scripts/stop_postgres.sh

migrate-postgres:
	@bash ./postgres-repository/scripts/migrate_postgres.sh

package:
	@mvn clean package

update-dependencies:
	@mvn versions:update-properties -DgenerateBackupPoms=false

test:
	@mvn test

run-ui:
	@npm run start --prefix web-ui