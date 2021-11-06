# RabbitMQ

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

https://techsparx.com/software-development/docker/damp/mysql-ssl-connection.html
https://www.xmodulo.com/enable-ssl-mysql-server-client.html
https://www.lesstif.com/java/java-keytool-keystore-20775436.html
https://stufeel.tistory.com/12
docker
https://stackoverflow.com/questions/59284095/how-to-use-encrypted-connection-with-mysql-on-docker

rabbitmq mock https://github.com/fridujo/rabbitmq-mock

amqp https://qpid.apache.org/
