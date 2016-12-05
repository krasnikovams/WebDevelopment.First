Login to AWS
============
ubuntu@54.210.52.144 (ppk key required)

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
in browser go to http://54.210.52.144/
verify message: "Welcome to nginx!"


Appendix: Setup EC2 instance
============================
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
java -version
sudo apt-get install maven
mvn -version
sudo apt-get install mc
mc (quit with F10)
sudo apt-get install nginx
sudo ufw allow 'Nginx HTTP'
sudo ufw enable
sudo ufw status
verify nginx access in browser as in "Access nginx"