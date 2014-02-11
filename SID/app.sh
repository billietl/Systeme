#!/bin/bash

usage(){
    echo $0 "<work size> <chunk size>"
    exit 1
}
if [ -z $1 ]
then
    usage
fi
if [ -z $2 ]
then
    usage
fi

let worker_numbers="`cat $OAR_FILE_NODES | wc -l`-1"
master=`cat $OAR_FILE_NODES | head -n 1` 
workers=`cat $OAR_FILE_NODES | tail -n $worker_number`
work_size=$1
chunk_size=$2

oarsh $master 'sh master.sh $chunk_size'

for host in $workers; do
    oarsh $host 'sh worker.sh $master'
done

time java -cp sid.jar sid.integrale.ApplicationImpl $master $work_size
echo "nombre de noeuds " $worker_number
echo "taille du travail " $work_size
echo "taille des morceaux " $chunk_size
