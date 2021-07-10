# springboot-grpc-java
springboot-grpc-java 연습용

클린코드는 여기서 확인
https://github.com/grpc/grpc-java

Boot는 아직 적용안됨.

## 적용기술

* gRPC
  * Protocol Buffer
* SpringBoot

## 실행 테스트

<pre>
$ gradle generateProto
실행파일 실행
</pre>

## 사용설명

1. *.proto를 만들고

2. build셋팅...은 가져다쓰면 될거고

3. Server 만들기\
:server/com.scriptonbasestar.helloworld.server.HelloWorldServer
```java
var server = ServerBuilder.forPort(port)
	.addService(new GreeterImpl())
	.build()
	.start();
```

4. client - server 코드 실행


domain, boot-server 미완성


## TODO

프로젝트 구성 변경

pure.server

boot.domain
boot.server

client - 포트로 접속하니까 양쪽 모두 접속가능하도록
