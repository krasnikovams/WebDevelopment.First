Login to AWS
============
ubuntu@54.164.127.35 (ppk key required)

Build project
=============
cd /home/ubuntu/prjs/WebDevelopment.First
mvn clean package

Launch jetty locally
====================
cd <project directory>
mvn jetty:run
in browser go to http://localhost:8080/WebDevelopment.First/krasnikova.html
verify message: "Krasnikova Maria"

Launch jetty
============
cd /home/ubuntu/prjs/WebDevelopment.First
mvn jetty:run (not tested yet)

Access nginx
============
in browser go to http://54.164.127.35/
verify message: "Welcome to nginx!"