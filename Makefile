#!make
include .env
export $(shell sed 's/=.*//' .env)

run-postgres:
	@bash ./postgres-repository/scripts/run_postgres.sh

stop-postgres:
	@bash ./postgres-repository/scripts/stop_postgres.sh

package:
	@mvn clean package

test:
	@mvn test

run-ui:
	@npm run start --prefix web-ui