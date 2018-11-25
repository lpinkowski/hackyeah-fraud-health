# hackyeah-fraud-health

## Prerequisites
To build and package the application you will need Java8 installed on your machine.

##Prepare DB data.
DB script based on client data are in database_data/health.tar.xz

###To extract
tar xf database_data/health.tar.xz

###You will get SQL script file which should be applied on DB

1. Use the MySQL command line client: mysql -h hostname -u user database < path/to/health.sql
2. Install the MySQL GUI tools and open your SQL file, then execute it
3. Use phpmysql if the database is available via your webserve

Don't forget to create indexes.

##Docker.
Application provide docker file which contains MySQL DB configured.
To use docker MySQL:

1. Go to docker directory
2. docker-compose up -d health-mysql
3. run sql script on DB - go to section Prepare DB data

## Build and run
###Build
1. Go to project directory
2. Run command `./gradlew clean build`

###Run
 To run application type: `java -jar ./build/libs/frauddetector.jar`

After proccessing has been finished result files delivered.

### Run without creating jar file
Go to project directory then execute `./gradlew bootRun`



