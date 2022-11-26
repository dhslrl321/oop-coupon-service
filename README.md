
# 객체지향과 도메인에 집중한 선착순 쿠폰 발급 서비스

### gradle module dependency diagram

![image](https://user-images.githubusercontent.com/48385288/203355330-aeddfdcf-3d7e-47f8-a281-3fdd2e7cd5f4.png)

- **appendix**
  - [API Specification](https://github.com/dhslrl321/oop-coupon-service/wiki/API-Specification)
  - [core domain modeling](https://github.com/dhslrl321/oop-coupon-service/wiki/domain-modeling)
  - [domain state diagram](https://github.com/dhslrl321/oop-coupon-service/wiki/domain-state-diagram)
  - [application UseCase Diagram](https://github.com/dhslrl321/oop-coupon-service/wiki/usecase-diagram)
  - [Intellij DSM](https://github.com/dhslrl321/oop-coupon-service/wiki/Intellij-DSM-(dependency-structure-Matrix))

# 프로젝트 실행 방법

### prerequisite

- java version: 11
- docker-engine: 20.10.12

### Spring Boot Application 실행시키기

Application 을 실행하기 위해서는 다음과 같은 절차가 필요합니다

1. build application
2. start local db
3. run application

아래 명령어로 실행시킬 경우 위 과정을 한번에 수행할 수 있습니다.

```shell
make run
```

### Health Check

아래 url 을 통해 application 의 health check 를 수행할 수 있습니다.

```shell
curl http://localhost:8080/health
```

### test & build

아래 명령을 통해 전체 테스트 및 빌드를 쉽게 수행할 수 있습니다.

```shell
make build
```

```shell
make test
```
