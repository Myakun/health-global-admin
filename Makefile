-include .env
export

docker-local-rebuild:
	docker compose -f docker-compose-local.yml stop
	docker compose -f docker-compose-local.yml build
	docker compose -f docker-compose-local.yml up -d --remove-orphans

build:
	./gradlew build -x test

clean:
	./gradlew clean

run:
	./gradlew bootRun

test:
	./gradlew test

migrate:
	./gradlew flywayMigrate

migrate-clean:
	./gradlew flywayClean

migrate-info:
	./gradlew flywayInfo

.PHONY: docker-local-rebuild build clean run test migrate migrate-clean migrate-info
