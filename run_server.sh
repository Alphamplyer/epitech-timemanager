#!/bin/bash

docker-compose down -v

cd "./timemanager-api"
mvn clean package -DskipTests
cd ".."

docker-compose up --build -d timemanager-postgresql

sleep 10

docker-compose up --build -d adminer
docker-compose up --build -d timemanager-back
