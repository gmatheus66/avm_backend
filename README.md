# avm_backend
API app Nuvem

## Requisitos

Java 1.8

Maven 3.6.3

Docker

Docker Compose

Mysql

## Run

Baixar Dependências 
```
$ mvn install -DskipTests
```

Compilar
```
$ mvn clean package -DskipTests
```

Build Docker
```
$ docker-compose build
```

Run
```
$ docker-compose up
```
or (Terminais diferentes)
```
$ docker-compose up mysql-app
```
and
```
$ docker-compose up spring-app
```

##
## Rotas

|   URL  |  Method  |    Headers   | Ex.||
| :---         |     :---:      |          :--- | :--- |:---:|
| localhost:8080/user   | POST    | Content-Type application/json   |Raw `{"username": "jurandir","email": "jurandir@login.com","password": "password"}`|Criar usuario|
| localhost:8080/user/{id}   | GET    |    |localhost:8080/user/1|Pegar Usuario por id|
| localhost:8080/file/user/{id}   | GET    |    |localhost:8080/file/user/1|Pegar arquivos referente ao usuario |
| localhost:8080/file/delete   | POST    | Content-Type application/json   |Raw `{"user_id": "1","file_id": "2"}`|Deletar um arquivo|
| localhost:8080/file    | POST      |       |Form-data `file=@/home/teste.img user_id=1`|Upload de um arquivo|
| localhost:8080/users   | GET     |       | |Pegar todos os usuarios||


[Postman para teste da API](https://www.postman.com/downloads/)
