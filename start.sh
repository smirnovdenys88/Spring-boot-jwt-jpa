#! /bin/bash
./wait-for-it.sh postgres:5432 -t 15
#this next line we add pg_user, pg_pass, port, host -d postgres
#PGPASSWORD=masterkey psql -h host-postgres -p 5432 -d db-postgresql -U pg_user
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar app.jar