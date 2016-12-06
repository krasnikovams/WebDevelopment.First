Login to AWS
============
ubuntu@54.196.76.192 (ppk key required)

Build project
=============
cd /home/ubuntu/prjs/WebDevelopment.First
mvn clean
mvn clean package

Launch jetty locally
====================
cd <project directory>
mvn jetty:run
in browser go to http://localhost:8080/WebDevelopment.First/krasnikova.html
verify message: "Krasnikova Maria"

Launch jetty on AWS
===================
cd /home/ubuntu/prjs/WebDevelopment.First
mvn jetty:run
in browser go to http://54.196.76.192/WebDevelopment.First/krasnikova.html
verify message: "Krasnikova Maria"

Access nginx
============
in browser go to http://54.196.76.192/
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
verify nginx access in browser as in "Access nginx"
ls /etc/nginx/nginx.conf
cd /etc/nginx
sudo mc
edit nginx.conf:
http {
  server {
      location /WebDevelopment.First/ {
          proxy_pass http://127.0.0.1:8080/WebDevelopment.First/;
      }
  }
}
sudo nginx -s reload
verify nginx again
cd ~ubuntu/
mkdir prjs
chmod 777 prjs/
ls -l
copy <project directory> to /home/ubuntu/prjs using winscp
"Build project"

cd /var/log/nginx/

sudo iptables -t nat -A PREROUTING -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080
"Launch jetty on AWS"