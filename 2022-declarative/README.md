# RabbitMQ

개발중에 상호참조 및 역참조 방지
하기위해 과도하게 모듈을 나눔

## Stacks

- springboot
- rabbitmq
- h2
- mysql
- jpa
- redis
- ssl 접속
- junit5
- webflux
- webclient
- skaffold
- kompose

## Install

```bash
minikube start
```

docker compose
```bash
skaffold init --compose-file docker-compose.yaml
```

kubectl
```bash
skaffold dev --no-prune=false --cache-artifacts=false
./gradlew ktlintApplyToIdea
```

## REF

skaffold
* https://skaffold.dev/docs/references/yaml/

* https://techsparx.com/software-development/docker/damp/mysql-ssl-connection.html
* https://www.xmodulo.com/enable-ssl-mysql-server-client.html
* https://www.lesstif.com/java/java-keytool-keystore-20775436.html
* https://stufeel.tistory.com/12

docker
* https://stackoverflow.com/questions/59284095/how-to-use-encrypted-connection-with-mysql-on-docker

* rabbitmq mock https://github.com/fridujo/rabbitmq-mock
* amqp https://qpid.apache.org/

* https://www.baeldung.com/spring-data-ddd
* https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/entity-listeners.html
* https://neo4j.com/developer/spring-data-neo4j/
* https://neo4j.com/developer/neo4j-ogm/
* https://neo4j.com/developer/java-procedureso/

* https://github.com/jankotek/mapdb
