#### MUSINSA_CATEGORY
#### musinsa assignment

# Getting Started with docker 

## Required Environments with Docker (recommended)
* Ubuntu 18.04 or 20.04 version recommended (AWS EC2)
* Docker

## set up example
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



========================
## Getting Started without docker

### Required Environments without Docker (deprecated)

* Ubuntu 18.04 or 20.04 version recommended (AWS EC2)
* JDK (>=11)
### set up example
```
sudo apt-get upgrade
sudo apt-get update
sudo apt-get install git
sudo apt-get install openjdk-11-jdk
```

### 1. git clone
```
git clone https://github.com/tngus3722/MUSINSA_CATEGORY.git
cd MUSINSA_CATEGORY
```

### 2. build jar
need java (>= 11)
```
./gradlew bootJar 
```

### 3. excute jar
```
java -jar build/libs/categoty-0.0.1-SNAPSHOT.jar.jar
```
or
```
java -jar build/libs/*.jar
```



# NOTE ! 

Because of flyway and H2db, you do not need any DB or DDL
Because of using h2 db in memory, datas in database are reset when restarted
initial category table values are inserted By flyway just use it !

Also you can use my test server with swagger-ui
```
http://52.78.30.91:8080/swagger-ui.html
```

thank you !


all test code are passed (21-01-06)


![image](https://user-images.githubusercontent.com/32263898/148392956-3089ed1f-0c3b-4de0-b3e2-d9fe81b658c6.png)

