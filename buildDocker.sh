#!/usr/bin/env bash

cd discovery-service;                ./gradlew build -x test; cd -
cd edge-service;                     ./gradlew build -x test; cd -
cd produto-service;                  ./gradlew build -x test; cd -
docker-compose up --build