## [본 과정] 이커머스 핵심 프로세스 구현
[단기 스킬업 Redis 교육 과정](https://hh-skillup.oopy.io/) 을 통해 상품 조회 및 주문 과정을 구현하며 현업에서 발생하는 문제를 Redis의 핵심 기술을 통해 해결합니다.

## 프로젝트 실행방법

프로젝트를 받은 뒤
> $ docker compose up -d 

위 명령어를 실행하면 schema와 data가 자동으로 생성됩니다.

그 후 api 모듈에 ApiApplication을 실행하면 프로젝트가 정상적을 작동합니다.

## 멀티모듈 구성
이 프로젝트는 **레이어드 아키텍처**를 기반으로 설계되었으며, 다음과 같이 3개의 모듈로 구성되어 있습니다.

### **API 모듈**
- **역할**  
  사용자 및 외부 시스템과의 상호작용을 담당하며, HTTP 요청을 처리하고 응답을 반환하는 역할을 합니다.
- **주요 책임**
    - RESTful API 제공
    - 요청 데이터 검증 및 변환
    - Application 모듈에 의존하여 비즈니스 로직 호출
- **의존성**
    - `application` 모듈

---

### **Application 모듈**
- **역할**  
  비즈니스 로직을 구현하는 핵심 계층으로, 도메인 모델과 외부 인터페이스(API 모듈)를 연결합니다.
- **주요 책임**
    - 비즈니스 규칙 및 유스케이스 처리
    - 트랜잭션 관리
    - Domain 모듈과의 상호작용
- **의존성**
    - `domain` 모듈

---

### **Domain 모듈**
- **역할**  
  프로젝트의 핵심 엔티티와 데이터 접근 로직을 관리합니다.
- **주요 책임**
    - 도메인 엔티티 정의
    - JPA 및 데이터베이스 접근
    - 도메인 규칙을 보장하는 로직 포함
- **의존성**
    - 독립적이며 다른 모듈에 의존하지 않음

---

## 테이블 설계
- **도메인**
  ![](https://velog.velcdn.com/images/kimbro97/post/060397e0-2ff7-4997-93d7-5e7c9c5da022/image.png)
- **테이블**
  ![](https://velog.velcdn.com/images/kimbro97/post/f3028e77-5355-476c-86e6-98c9c01b1aca/image.png)

Movie(영화)와 Theater(극장)은 M:N 관계이므로 MovieTheater로 일대다 다대일 관계로 설계하였습니다.
Movie(영화)와 Screening(상영)은 1:N 관계이므로 일대다 관계로 설계하였습니다.
Screening(상영)과 Seat(좌석)은 1:N 관계이므로 일대다 관계로 설계하였습니다.