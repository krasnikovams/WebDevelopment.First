Login to AWS
============
ubuntu@54.164.127.35 (ppk key required)

Build project
=============
cd /home/ubuntu/prjs/WebDevelopment.First
mvn clean package

Launch jetty
============
cd /home/ubuntu/prjs/WebDevelopment.First
mvn jetty:run (not tested yet)

Access nginx
============
in browser go to http://54.164.127.35/
verify message: "Welcome to nginx!"