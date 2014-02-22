#!/bin/bash

mkdir res
cd res
mv ../results.tgz .
tar xvf results.tgz
for job in `cat jobs`
do
    echo `cat OAR.$job.stdout|grep "nombre de noeuds"|cut -f 2`" "`cat OAR.$job.stdout|grep "taille des morceaux"|cut -f 2`" "`cat OAR.$job.stderr|grep "real"|cut -f 2`" "`cat OAR.$job.stdout|grep "taille du travail"|cut -f 2` >> ../results.csv
done
cd ..
rm -r res
