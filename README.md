# 프로그래머스 데브코스 1차 프로젝트 : 영화 예매

이 프로젝트는 프로그래머스 데브코스 1차 프로젝트인 클론 코딩의 spring boot 저장소 입니다.

[FE 저장소](https://github.com/park-se-jun/programmers-1st-clone-project-react)

1차 프로젝트 진행 기간은 아래와 같습니다.

    - 2022-11-21(월) : 프로젝트 시작일
    - 2022-12-05(일) : 프로젝트 1차 제출 기한 ❌(제출 실패)
    - 2022-12-11(일) : 프로젝트 2차 제출 기한 ✅(제출 성공!)

앞으로 추가될 내용들이 기록될 공간입니다.

    -
## 프로젝트 목적

프로그래머스 개인 프로젝트 인 [Spring Boot 상품 관리 API 구현](https://github.com/prgrms-be-devcourse/react-springboot-rest-api)을 강의 내용을 바탕으로 cloning 하고 API를 구현하는 1차 과제입니다.

저는 도메인을 영화로 변경한 후 DB 설계, API 설계, spring boot, react코드 작성을 진행하였습니다.

- 도메인에 맞는 DB를 생각하고 설계합니다.
- 사용자의 행동을 중심으로 도메인에 맞는 API 설계을 처음부터 끝까지 해봅니다.
- spring boot를 이용해 rest api 를 개발합니다.
- api에 대응하는 프론트엔드 개발도 진행합니다.
- react 및 spring boot의 사용법을 익힙니다



## 프로젝트 설계 방식

본 프로젝트를 진행하면서 저는 아래의 과정을 거쳐 개발을 진행 하였습니다.
1. 도메인 선정
2. figma를 활용한 페이지별 필요한 데이터 정리.
3. db 설계(erd 그리기)
4. 1번을 바탕으로 기능 설계 및 API 설계
5. API 개발
6. react 개발
7. 버그 수정

### 도메인 선정
원래 주제는 front end 즉 도메인이 커피숍 주문관리로 고정된 상태로 API를 설계하는 과제였습니다. 저는 좀 욕심을 내서 그냥 주어진 프론트와 db를 가지고 API코드르 짜는것 보다 주문 관리의 도메인 내에서 다른 주제를 선정하고 해당 주제에 대한 DB 설계와 API 설계 프론트 설계를 하고싶었습니다.

주문관리중 내가 배울 것이 많은 게 뭐가있을까 고민하다 우리가 실제도 많이 사용해 프론트엔드 화면에 대한 레퍼런스를 구하기 쉽고, 관련된 data도 많이 필요할 것 같은 영화 예매 프로그램으로 도메인을 변경을 결정한 후 개발을 시작했습니다.
### 필요 데이터 정리
우선적으로 Figjam을 활용해 [MegaBox](https://www.megabox.co.kr/booking)와 생각해본 내용을 바탕으로 예상 화면을 뽑고,
해당 화면에서 사용자가 할 수 있는 상호작용을 정리한 후, 필요할 것으로 예상되는 데이터(스키마)를 산출헀습니다.
그리고 시간 상의 문제로 먼저 취해야할 부분을 선정 하여 우선순위를 두었습니다.

![상호작용정리](./docs/1차프로젝트%20설계%20-%20페이지%20구상.png)
![스키마정리](./docs/1%EC%B0%A8%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EC%84%A4%EA%B3%84%20%EC%8A%A4%ED%82%A4%EB%A7%88.png)
### db 설계

사용자에게 보여지는 화면을 기준으로 생각한 스키마를 ER 다이어그램을 그리며 조금씩 고도화 시켰습니다.
![DB 고도화](./docs/erd%20%ED%9D%94%EC%A0%81/%EC%98%81%ED%99%94%20%EC%98%88%EB%A7%A4%20%EC%84%A4%EA%B3%84_221204_033737_5.jpg)


최종적으로 사용한 DB 구조는 다음과 같습니다.

![최종DB](./docs/%EC%B5%9C%EC%A2%85DB.png)

### 기능 정리,API 설계

기능을 정리한 것으로 바탕으로 생각한 API의 모습과 실제 구현한API의 모습은 다음과 같습니다. 우선순위에 따라 모든 사항을 구현하진 못했습니다.

#### 설계 API
![설계API](./docs/api%EC%84%A4%EA%B3%84.png)


#### 구현된 API

##### `POST`
- Schedule
```
/api/schedules
```
    request: {
        startTime:yyyy-mm-ddThh:mm,
        movieId:UUID(String),
        screenId:UUID(String)
    }
    response: -
- Reservation
```
/api/reservations
```
    request: {
        scheduleId:UUID (),
        phoneNumber: 000-0000-0000(String),
        price: Number,
        rowArray: String[],
        colArray: Number[]
    }
    response: {
        movieReservationId: UUID(String)
    }
##### `GET`
- Health
```
/health
```
    request: -
    response: "health"

- Movie

```
/api/movies/active
```
    request: -
    response: [
        {
            movieId: UUID(String),
            title: UUID(String),
            releaseDate: XXXX-XX-XX(String),
            psterUrl: url(String)
        }
    ]

- Theater
```
/api/theaters
```
    request: -
    response: [
        {
            theaterId: UUID(String),
            theaterName: String
        }
    ]

- Schedule
```
/api/schedules?theaterId={theaterId}&date={date}&movieId={movieId}
```
    Request Param: 
        - theaterId : UUID(String)
        - date : yyyy-mm-dd (String) default: today
        - movieId : UUID(String)
    request: -
    response: [
        {
            scheduleId: UUID (String),
            title: String,
            startTime: yyyy-mm-ddThh:mm,
            endTime: yyyy-mm-ddThh:mm,
            theaterName: String,
            screenName: String,
        }
    ]
```
/api/schedules/{scheduleId}
```
    Path Variable: 
        - scheduleId : UUID(String)
    request: -
    response: {
        scheduleId,
        title,
        startTime,
        endTime,
        theaterName,
        screenName
    }
```
/api/schedules/{scheduleId}/seats
```
    Path Variable: 
        - scheduleId : UUID(String)
    request: -
    response: {
        a:[...true,true,true,false],
        b:[...true,true,true,false],
        ...
    }   ( Map<String,Boolean[]> )
- Reservation
```
/api/reservation/lookup/{userPhone}
```
    Path Variable: 
        - userPhone : 000-0000-0000 (String)
    request: -
    response: [
        {
            movieReservationId,
            phone: UUID(String),
            seatCount: Number,
            price: Number,
            movieTitle: String,
            movieStartTime: hh:mm:ss,
            movieEndTime: hh:mm:ss,
            date: yyyy-mm-dd,
            rowArray: String[],
            colArray: Number[]
        }
    ]
##### `PUT`
😭

##### `DELETE`
😭
### 개발 언어
- front-end
    - 프레임워크 : react
    - 주요 의존성:
        - axios
        - recoil
        - bootstrap
        - react-seat-picker
        - react-modal
- back-end
    - 프레임워크 : spring boot
    - 주요 의존성 :
        - spring-boot-starter-jdbc
        - spring-boot-starter-web



## 실제 프로젝트 화면

- 첫페이지
  ![첫페이지](./docs/%ED%99%94%EB%A9%B4%EA%B5%AC%EC%84%B1/%EC%B2%AB%ED%8E%98%EC%9D%B4%EC%A7%80.png)
- 좌석선택 페이지
  ![좌석선택](./docs/%ED%99%94%EB%A9%B4%EA%B5%AC%EC%84%B1/%EC%A2%8C%EC%84%9D%20%EC%84%A0%ED%83%9D.png)
- 예매 성공시 alert창
  ![예매성공](./docs/%ED%99%94%EB%A9%B4%EA%B5%AC%EC%84%B1/%EC%98%88%EB%A7%A4%EC%84%B1%EA%B3%B5.png)
- 조회 모달
  ![조회모달](./docs/%ED%99%94%EB%A9%B4%EA%B5%AC%EC%84%B1/%EC%A1%B0%ED%9A%8C%20%EB%AA%A8%EB%8B%AC.png)
- 조회 결과
  ![조회결과](./docs/%ED%99%94%EB%A9%B4%EA%B5%AC%EC%84%B1/%EC%A1%B0%ED%9A%8C%20%EA%B2%B0%EA%B3%BC.png)

## 느낀점

해당 프로젝트를 진행하면서 느낀 점은 DB 를 설계하는 것이 보통일이 아니라는 생각이 들었습니다.
ERD 도 그릴줄 몰랐고, 정규화 같은것도잘몰랐을 뿐더러 DB 를 설계하는게 이 프로젝트가 처음이었기 때문에 DB 를 설계하는데 엄청난 시간이 들어갔습니다. DB 설계가 완료된 후에도 크고 작은 변경사항들이 계속 있었기 때문에 테이블의 이름도 계속 바뀌고 길어지는 등의 문제가 있었습니다.

그리고 DB 설계가 중요한 이유도 뼈저리게 느꼈습니다. db 가 복잡해 지니 원하는 데이터를 가져오기 위한 join 문도 점점 복잡해져 갔고, join 문이 복잡해지다 보니, jdbc 템플릿을 이용할때 오타를 찾기도 힘들고,버그를 잡기도 점점 복잡해져 갔습니다. 실제 회사들의 DB 설계를 한번 경험해 보고 싶다는 생각도 가지게 되었습니다.

또한 api 설계는 고려할게 많구나 라고 느꼈습니다. uri 를 이렇게 주는게 맞을지, 이 정보를 request body 와 request param, path variable 중 무엇으로 넘겨 주어야 할지, response 는 무엇을 주어야할지, 등등 크고 작은 고려할 것들이 많았습니다. 경험을 많이 하는것으로 어떤게 best prectice 일지 알아가야 할 것 같습니다.

api 가 안나오는 이유를 알게 되었습니다. 프론트 엔드 개발자로 백엔드 개발자와 협력할 떄는 왜 API 를 안주지?? 데이터를 안주니까 할 일이 없다느 생각만 했었는데 저 혼자 프론트와 백엔드까지 모든 분야를 진행해보니 백엔드 개발자 입장에서는 프론트가 어떤 데이터가 필요할지 알기 힘들 것 같다는 생각이 들었습니다. 프로젝트를 진행하는데 API 의 설계는 전적으로 백엔드의 책임이라고 생각했는데 그게 아니라 우선 프로젝트의 도메인을 가지고 프론트에서 필요한 데이터, 조건등을 충분히 얘기한 뒤 백엔드가 그 정보를 바탕으로 DB 를 설계해 api를 제공하는 방식으로 백엔드와 프론트엔드가 함께 설계해 나가야 하는 구나 라는 것을 새삼 깨닫게 되었습니다.

아래로는 1차 프로젝트를 진행하면서 한 주요 고민사항과 기억에 남는 문제해결 그리고 아쉬운 점을 적어보았습니다.

### 주요 고민 사항과 기억에 남는 trouble shooting

- 극장, 상영관, 좌석은 1:N 관계인가? N:M 관계인가? 🤔
    - 극장과 상영관 좌석이 1:N 관계일지, N:M 관계일지 어떤 방식으로 모델링을 해야할지 한참을 고민했었습니다. 두 방법 중 아직도 무엇이 정답인지 알 수 없는 상태지만, N:M관계가 db를 덜차지할 것 같다는 점이 있었고, 1:N 관계는 관계테이블을 만들지 않아도 됐습니다. 그래서 일단은 1:N관계로 각각을 모델링 하였습니다.
- erd 그리기, 약한 개체와 강한 개채??? 😵
    - db를 설계하면서 erd 의 필요성을 느끼게 되었습니다. erd를 살짝 보고 극장, 상영관, 좌석 테이블 간의 관계를 설정할 때 1:N 관계로 보게 된다면, `상영관은 극장이 없으면 존재할수 없고 좌석은 상영관이 없으면 존재할 수 없을 것 같은데 그럼 이것들은 약한 객체인가?` 라는 고민이 들었습니다. 하지만 그렇게 될경우 **약한객체는 복합키로 ID(많은 예제들이 그렇게 안내함)** 를 가져야 하기 때문에, 좌석의 경우에는 극장의 ID, 상영관의 ID, 좌석의 행번호, 좌석의 열번호 까지 모두 가지게 될 것 같았습니다. 이것과 관련해서 다른 개발자 친구에게 물어보니 SQL 스럽지 못하고 오히려 No SQL 에 가까운것 같다는 이야기를 듣고, 약한 객체긴 하지만 유니크한 ID 컬럼을 따로 두는 형태로 DB를 설계하였습니다.
- entity와 DTO를 어디에서 변환해 줘야 할까? 🤔
    - entity와 DTO의 사용위치에 대해 고민하게 되었습니다. model(entity) 객체를 controller 까지 올려도 될까? 반대로, DTO를 repository 까지 내려도 될까? 라는 고민을 가지게 되었는데 사람마다 얘기가 많이 달라서 아직도 답을 모르겠습니다..
- 너무 많아지는 것 같은 DTO들
    - DTO 가 너무 많아지는 것 같다는 생각이 들었습니다. 네이밍도 점점 어려워 졌고, 이를 해결할 수 있을 방법이 없을까 고민해 보았습니다. 아직 프로젝트에 적용시키지는 못했지만 inner class 로 관리 하는 방법을 찾아보게 되었습니다.
- entity란 뭘까...? 😵
    - 프로젝트를 진행하면서 entity가 뭘까에 대한 의문이 들기 시작했습니다. 저는 이 프로젝트에서 join을 해서 가져온 정보들로 하나의 객체를 만들었는데 JPA 같은 것을 생각할 때 Entity는 하나의 테이블과 매핑되는 관계인데 어떻게 해야할까? 라는 의문이 들었습니다. 제 나름대로 생각해 보기에는 JPA의 경우 영속성 컨텍스트로 관리되기 때문에, Join으로 가져오지 않아도 Entity를 영속성 컨텍스트 내에서, 연관관계 있는 객체로 가져올 수 있기 때문에, JPA를 한다면 그런 식으로 하는게 맞고, 만약 JPA를 사용하지 않는다면, 여러 테이블에 산재해 있는 정보들을 join으로 모아 하나의 엔티티로 만드는게 맞는것 같다.라는 생각을 가지게 되었습니다.
- A service에서 A repository가 아니라 B repository를 사용해도 될까? B Service는? 🤔
    - A Service에서 A repository 가 아닌 B repository를 사용해도 될까? 라는 생각이 들었습니다.이것과 관련해서 관련 코드들을 찾아봤는데 큰 문제는 없어 보였습니다. 오히려 A Service에서 다른 서비스인 B service를 사용하는 경우 순환참조의 문제가 발생할 수 있다는 블로그 글을 보았습니다. 그렇기 때문에 B service를 사용하지 말고, 차라리 B repository를 사용하라는 내용의 글이었는데 그 글 덕분에 고민이 해결되었습니다.

### 아쉬운점
- rest API의 구현과제인데 API 개발을 전부 진행하지 못했다.
    - 가장 아쉬운점 중 하나로 rest API 구현 과제인데 DB 모델링, 프론트엔드 코드 작성 등의 문제로 API 작성을 끝까지 하지 못하였습니다.
- 더 좋은 DB 모델링을 할 수 있지 않았을까?
    - 개발을 진행하기에 앞서 내가 한DB 모델링이 최선일까? 더 나은 DB 모델링이 있지 않을까? 라는 생각이 들었습니다. 이는 개발을 진행하면서도 마찬가지였는데, 특히 예매인 Reservation과 관련된 작업을 할때 격하게 느꼈습니다. Resrvation을 만든 뒤에 해당 Resrvation이 가지고 있는 예약된 자석들을 insert 해주어야 했습니다. reservation_seat은 schedule에 연결된 Screen의 seat에서 사용자 입력과 일치하는 좌석의 ID를 알아야 했는데 해당 로직을 구현하는 SQL이 쉽지 않았습니다.그래서 더 나은 모델링이 있지 않았을까? 하는 아쉬움이 남았습니다.
- 테스트를 전혀 하지 못했다.
    - 프로젝트를 진행하면서 시간내에 개발하는데 급급해 테스트를 거의 하지 못했습니다. 추가적으로 개선할 때는 이렇지 않도록 해야겠습니다.
- api 셜계를 더 체계적으로 할 순 없을까?
    - API 설계 역시 처음이다 보니, 너무 주먹구구식으로 만든것 같았습니다. 더 체계적으로 할 수 있었으면 좋았을 것 같습니다.
- database의 네이밍이 중구난방이다 (통일되지 않은 유비쿼터스 언어).
    - database의 table 명이 계속 중구난방으로 변해 통일되지 않은 유비쿼터스 언어를 가진 상태입니다. 어디서는 Cinema 가 Theater 로 쓰이고, 또 어디서는 Theater 가 Screen으로 쓰이며 또 어디서는 Movie Ticket 이 Movie Reservaion 이 혼용되어 사용되고 있는 상태입니다.table의 컬럼을 하나 건드리면 작성된 sql 문들이 전부 고장나는 상태기 때문에 수정할 엄두를 내지 못했습니다. 그러다보니 저 혼자 코드를 보기도 힘드러 졌고, 다른 사람은 더욱 코드르 보기 힘들 것 같다는 생각이 들었습니다. 이런일이 발생하지 않도록 개선시켜야할 것 같습니다.
- 많은 시간을 사용했음에도 무결성을 보장하지 못하는 Database
    - 시간을 많이 써서 설계했음에도 DB 가 무결성을 보장하지 못하는 점이 아쉬웠습니다. 하나의 상영관에서 영화가 끝나지 않았음에도 다른 영화가 상영되는 스케쥴이 존재할 수 있다거나 하는등의 문제가 발생할수 있는 형태였기 때문에 아쉬웠습니다.
- validation 코드가 전무하다..!
    - 기능개발에 급급해 값을 검증한는 validation 로직이 없는 점 역시 굉장히 아쉬운 부분이었습니다.
- 커밋 관리를 제대로 하지 못했다.
    - 이는 내가 해야할 task 를 제대로 정리하지 못했다로 정리될 수 있을 것 같습니다.보다 확실하게 task 를 나누고, 브랜치 관리 전략을 세웠다면 하나의 커밋에 동시에 여러 기능이 개발되는 일이 없을 수 있었는데 그렇게 하지 못했습니다.
- 지정된 1차 마감기한을 준수하지 못했다.
    - 개발자로 취업을 준비한다면 deadLine을 맞추는것 역시 중요한 역량중 하나라고 생각합니다.저는 해당 프로젝트를 진행하면서 도메인 선정,예상 페이지의 상호작용 정리, DB 설계, 서버사이드 코딩, 프론트 사이드 코딩을 하여 마감기한을 맞추지 못했습니다. 자신의 개발 능력과, 도메인의 범위를 명확하게 파악했으면 이런일이 일어나지 않았을텐데 하는 아쉬움이 있었습니다.

## 향후 목표

- 구현되지 않은 API들 추가
- 관리자 페이지 추가
- test코드 작성
- validation 코드 추가
- database 네이밍 통일 구조 개선
