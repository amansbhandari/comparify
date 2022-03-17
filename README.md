# Comparify #

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=comparify&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=comparify) [![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=comparify)](https://sonarcloud.io/summary/new_code?id=comparify) [![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=comparify)

## Steps to Run the Application ##

### Run Comparify Server ###

1. `cd server`
2. `mvn clean install`
3. `java -jar target\comparify-server.jar`

### Run Comparify Client ###

1. `cd client`
2. `npm i`
3. `npm run serve`

### To setup the database ###

You need to figure out environment - dev | prod | staging | test
1. `cd server/misc/dummy\ data/`
2. `bash importData-<enviroment>.sh`
Note1: If you want to import data for all environments at once, Replace your 2nd command with
`bash *.sh`

Note2: Windows users might need to install WSL(Windows Subsystem for Linux)
