#!/bin/bash

./home/ubuntu/server/server_stop.sh

rm /home/ubuntu/server/*.jar

mv /home/ubuntu/server/build/libs/*.jar /home/ubuntu/server