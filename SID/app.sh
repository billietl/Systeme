master=`cat $OAR_FILE_NODES | head -n 1` 
workers=`cat $OAR_FILE_NODES`

oarsh $master 'rmiregistry &'
oarsh $master 'sh master.sh $master'

for host in $workers; do
    oarsh $host 'sh worker.sh $master'
done

java -cp sid.jar sid.integrale.ApplicationImpl $1
