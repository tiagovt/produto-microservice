#!/usr/bin/env bash

cd config-server;                     ./gradlew build -x test; cd -
cd discovery-service;                ./gradlew build -x test; cd -
cd edge-service;                     ./gradlew build -x test; cd -
cd produto-service;                  ./gradlew build -Pspring.profiles.active=docker -x test; cd -
docker-compose up --build