#!/bin/env python

input = open("results.csv")
speed = dict()

for l in input:
    # minage des donnees
    if l == '"nombre d\'esclaves" "taille des chunks" "temps de travail" "taille du travail"\n':
        continue
    splitted = l.split(" ")
    nb_noeuds = int(splitted[0])
    chunk_size = int(splitted[1])
    job_size = int(splitted[3][:-1])
    min, sec = splitted[2].split("m")
    if "s" in sec:
        sec = sec[:-1]
    time = (float(min)*float(60))+float(sec)

    # mise en sdd
    if not job_size in speed:
        speed[job_size] = dict()

    if not nb_noeuds in speed[job_size]:
        speed[job_size][nb_noeuds] = dict()

    if not chunk_size in speed[job_size][nb_noeuds]:
        speed[job_size][nb_noeuds][chunk_size] = (0.0,0)

    acc = speed[job_size][nb_noeuds][chunk_size][0] + time
    nb = speed[job_size][nb_noeuds][chunk_size][1] + 1
    speed[job_size][nb_noeuds][chunk_size] = (acc,nb)

# converstion en moyennes
for js in speed:
    for nn in speed[js]:
        for cs in speed[js][nn]:
            moy = float(speed[js][nn][cs][0]) / float(speed[js][nn][cs][1])
            speed[js][nn][cs] = moy

# convertion en speedup
# for js in sorted(speed):
#     for nn in sorted(speed[js]):
#         for cs in sorted(speed[js][nn]):
#             speedup = float(speed[js][1][cs]) / float(speed[js][nn][cs])
#             speed[js][nn][cs] = speedup

# ecriture dans les fichiers
for js in sorted(speed):
    output = open("travail_"+str(js)+".csv", "w")
    for nn in sorted(speed[js]):
        for cs in sorted(speed[js][nn]):
            output.write(str(nn)+" "+str(cs)+" "+str(float(speed[js][1][cs]) / float(speed[js][nn][cs]))+"\n")
        output.write("\n")
    output.close()
