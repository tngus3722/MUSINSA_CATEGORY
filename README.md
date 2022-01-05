# MUSINSA_CATEGORY
musinsa assignment

# Required Environments with Docker 
* Ubuntu 18.04 or 20.04 version recommanded (AWS EC2)
* Docker

# Getting Started with docker 
## 0. download docker
```
sudo apt-get upgrade
sudo apt-get update
sudo apt -y install docker.io
```
## 1. pull docker image
```
sudo docker pull tngus3722/musinsa
```

## 2. run docker container
```
sudo docker run -d -p 8080:8080 tngus3722/musinsa
```





# Getting Started without docker
## Required Environments without Docker
* Ubuntu 18.04 or 20.04 version recommanded (AWS EC2)
* JDK (>=11)
* 
```
sudo apt-get upgrade
sudo apt-get update
sudo apt-get install git
```
* git clone
```
git clone https://github.com/tngus3722/MUSINSA_CATEGORY.git
cd MUSINSA_CATEGORY
```

## 1. build gradle
```
./gradlew bootJar (need java (>= 11)
```





#Note
#### Because of flyway and using H2DB, you need not any DB or DDL
#### initial category table values are inserted By flyway just use it !
## Also you can use my test server with swagger-ui
```
http://52.78.30.91:8080/swagger-ui.html
```

thank you !
