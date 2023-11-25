#!/bin/bash

JAR=backend-0.0.1-SNAPSHOT.jar
LOG=/dev/null
#LOG=gamsung-console.log
export spring_profiles_active=prod

./home/ubuntu/server/scripts/server_stop.sh

cd ~/server
nohup java -jar $JAR > $LOG 2>&1 &
