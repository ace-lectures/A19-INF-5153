#!/usr/bin/env bash

MODE=REGULAR
#MODE=SONAR

for team in is*
do
    cd $team
    if [ $MODE = "REGULAR" ]
    then
      mvn clean install -DskipTests
    else
      mvn org.jacoco:jacoco-maven-plugin:prepare-agent clean install
      mvn sonar:sonar
    fi
    cd ..
done
