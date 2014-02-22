#!/bin/env python
input = open("results.csv")
#output = open("results.ok.csv", "w")
last_line = ["", "", "", ""]
speed = dict()
#output.write("# nb_worker chunk_size speed_up work_size")
for l in input:
    splitted = l.split(" ")
    if (splitted[0]==last_line[0]) and (splitted[1]==last_line[1]) and (splitted[3]==last_line[3]):
        print("redondance")
    else:
        min, sec = splitted[2].split("m")
        time = (int(min)*60)+float(sec[:-1])
        output = open("travail_"+splitted[3][:-1]+".csv", "a")
        if splitted[0]=="1":
            key = splitted[0]+splitted[1]+splitted[3]
            speed[key] = time
            speedup = 1
        else:
            speedup = speed["1"+splitted[1]+splitted[3]]/time
        if not splitted[0]==last_line[0]:
            output.write("\n")
        output.write(splitted[0]+" "+splitted[1]+" "+str(speedup)+" "+splitted[3][:-1]+"\n")
        output.close()
    last_line = splitted

