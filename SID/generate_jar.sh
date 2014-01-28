#!/bin/bash

rm sid.jar 2>/dev/null
rm bin.* 2>/dev/null
javac -classpath src -d bin src/sid/pull/WorkerImpl.java &
javac -classpath src -d bin src/sid/pull/MasterImpl.java &
javac -classpath src -d bin src/sid/push/WorkerImpl.java &
javac -classpath src -d bin src/sid/push/MasterImpl.java &
javac -classpath src -d bin src/sid/integrale/ApplicationImpl.java &
javac -classpath src -d bin src/sid/pi/ApplicationImpl.java &
cd bin
jar cf ../sid.jar ./*
rmic -classpath ../sid.jar sid.pu{sh,ll}.MasterImpl
rm ../sid.jar
jar cf ../sid.jar ./*
cd .. 
