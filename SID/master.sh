#!/bin/bash

usage(){
    echo $0 "<taille des morceaux de travail>"
    exit 1
}

if [ -z $1 ]
then
    usage
fi

java -cp sid.jar sid.pull.MasterImpl $1 &
