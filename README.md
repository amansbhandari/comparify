# Comparify #
 [![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=comparify)](https://sonarcloud.io/summary/new_code?id=comparify)  [![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=comparify)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=comparify&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=comparify) [![Qscored Score](https://qscored.com/badge/ee2bfbf02818762a4736efda23ce7d079e6057b846d07bf78a7c3f0b02c6cf05/score/)](https://qscored.com/summary/145ef366-ace3-4f34-8e5c-d05128908504/506/)  [![Qscore rank](https://qscored.com/badge/ee2bfbf02818762a4736efda23ce7d079e6057b846d07bf78a7c3f0b02c6cf05/rank/)](https://qscored.com/summary/145ef366-ace3-4f34-8e5c-d05128908504/506/)
  
  
### The Web Application is used to compare the price of the products across retail stores

## Steps to Run the Application ##

### Run Comparify Server ###

1. `cd server`
2. `mvn clean install`
3. `java -jar target\comparify-server.jar`

 
### Run Comparify Client ###
 
1. `cd client`
2. `npm i`
3. `npm run build`
4. `node server/index.js`
