#!/bin/bash

rm sid.jar 2>/dev/null
cd bin
jar cf ../sid.jar ./*
rmic -classpath ../sid.jar sid.pu{sh,ll}.{Master,Worker}Impl
rm ../sid.jar
jar cf ../sid.jar ./*
cd .. 
