## 데이터베이스
- 기본 개념: 필드, 데이터베이스, 엔티티, 스키마, DBMS
- Connection Pool
- RDBMS
  - 기본 개념: 테이블/ CK, PK, FK
  - 종류: Oracle, MySQL, SQLite
- 조인
- 트랜잭션
- 정규화
- 인덱스를 사용하는 이유와 장단점
- ORM
- NoSQL
  - 기본적인 특징
  - 종류: HBase/ Cassandra/ MongoDB
<br><br><br>


### 기본 개념: 필드, 데이터베이스, 엔티티, 스키마, DBMS
- 필드: 특정한 타입의 데이터를 저장하기 위한 영역. 속성과 동일한 개념
- 데이터베이스: 필드들이 모여서 레코드를 이루고, 레코드가 모여서 파일을 이루고, 파일들의 집합을 데이터베이스라고 함
- 엔티티(Entity): 사람, 장소, 사물 등과 같이 독립적으로 존재하면서 고유하게 식별이 가능한 실세계의 객체
- 엔티티 집합(Entity Set): 동일한 속성을 가진 엔티티들의 집합. 예를 들어 모든 사원(엔티티)들은 EMPLOYEE라는 엔티티 집합을 이룸
- 스키마: 데이터베이스를 구성하는 데이터의 종류와 구조 및 이들간의 관계를 정의하는 기술과 명세
- 데이터베이스 관리 시스템(DBMS): 데이터 베이스를 정의하고, 구축하고, 조작하고, 제어하여 정보를 쉽게 활용할 수 있도록 하는 프로그램  
<a href="#top">TOP</a>

### Connection Pool
- DB와 연결된 Connection을 미리 만들어서 pool 속에 저장해두고 필요할 때 Connection을 꺼내어 쓰고 사용한 후 다시 Pool에 반환하는 기법. 동시 접속자가 가질 수 있는 Connection을 하나로 모아놓고 관리하는 개념. Pool에 남아있는 Connection이 없는 경우에 클라이언트는 대기 상태로 전환되고 여분의 Connection이 들어오면 주어짐
- 웹 서버의 경우 컨테이너가 실행될 때 Connection 객체가 미리 생성되기 때문에, 어플리케이션 동작 동안 Connection 생성이나 삭제 시간이 들지 않아서 동시 접속자 수가 많더라도 DB에 대한 오버헤드가 방지됨. 또한 한번에 생성될 수 있는 Connection 수가 제어되기 때문에 어플리케이션이 쉽게 다운되지 않음
- 자바 프로그램에서 DriverManager.getConnection()을 통해서 DB Connection을 얻는 경우는 사용자 요청시마다 해당 driver를 로드하고 id, pw를 통해 DB에 연결하여 하나의 Connection을 얻어오고 사용 후에 다시 연결을 해제하는 방식이기 때문에 비효율적이다. 따라서 jsp에서 context.xml 파일에 dataSource 설정을 미리해두고 (여기서 최소 유지 Connection 수나 최대 가용 Connection 수 등을 설정함), DAO에서는 커넥션 풀 관리 객체인 InitialContext 생성 후 context.lookup()으로 dataSource를 얻고 dataSource.getConnection()으로 미리 생성된 Connection을 사용하는 방식을  
<a href="#top">TOP</a>

### RDBMS
#### 기본 개념: 테이블/ CK, PK, FK
- 데이터를 행과 열로 구성된 이차원 테이블(관계와 동일 개념)의 집합으로 표현한 모델
- 테이블 내의 필드 중 일부를 다른 테이블의 필드와 중복함으로써 테이블 간의 상관 관계를 정의함
- 각 열을 속성(attribute), 각 행을 튜플이라고 표현함 (레코드와 동일)
- 후보키(candidate key): 유일성과 최소성(식별에 필요한 정보만 가짐)을 만족하는 속성
- 주키(primary key): 후보키 중에서 가장 적합한 식별자로 선정된 키
- 외래키(foreign key): 다른 테이블의 주키를 가져와서 해당 테이블의 속성으로 사용할 때 이를 외래키라고 함

#### 종류: Oracle, MySQL, SQLite
1) Oracle
  - 여러 서버에 대용량 DB를 분산함 -> 대규모 트랜잭션 로드를 처리할 수 있음
  - 절차적 언어 (PL/SQL)을 통해서 내장된 프로그램의 작성을 지원함
2) MySQL
  - 단일 DB로 제한되어 있어서 매일 수백만법 액세스되는 대용량 DB에는 부적합
  - 복원지점을 설정하는 저장점이 없음 (COMMIT, ROLLBACK만 존재)
3) SQLite
	- 서버 프로세스가 없는 시스템 임베디드 데이터베이스
	- 일반 디스크 파일을 직접 읽고 쓰는 형태
  - 가볍고 안정적으로 구동되기 때문에 모바일 환경에 많이 내장되어 사용됨  
<a href="#top">TOP</a>

### 조인

### 트랜잭션

### 정규화
- 개념: 데이터의 중복을 줄이고 무결성을 향상시키기 위해 데이터 베이스를 구조화하는 것
- 장점: 데이터를 변경할 때 일어나는 이상 현상들을 제거할 수 있음
- 단점: 테이블이 계속 잘게 분해되어서 JOIN 연산이 많아지고 응답 시간이 길어질 수 있음 -> 이런 현상이 심할 경우 반정규화(중복 통합)을 수행한다.
- [참고 링크1](https://wkdtjsgur100.github.io/database-normalization/)
- [참고 링크2](https://beansberries.tistory.com/entry/%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A2%85%EC%86%8D%EC%84%B1%EA%B3%BC-%EC%A0%95%EA%B7%9C%ED%99%94)

#### 제 1 정규화
1) 테이블 내의 모든 도메인이 원자값만으로 되어 있음 (1개의 값을 가짐)
2) 어떤 속성도 반복되어 나타나지 않음
3) 만약 원자값이 아닌 컬럼이 있다면 레코드를 나누고, 이것을 별도의 테이블로 만듦
- 문제: 기본키에 부분 함수적 종속으로 인한 이상 현상 발생 (삽입 이상, 삭제 이상, 갱신 이상)
-> 부분 함수적 종속인 컬럼을 따로 떼어내어 별도의 테이블로 만들면 제2정규형이 됨

#### 제 2 정규화
- 기본키에 속하지 않은 모든 속성이 기본키에 완전 함수적 종속성을 지님
- 함수적 종속: X의 값에 따라 Y의 값이 결정될 때 Y는 X에 대해 함수족 종속이라고 한다.
- X1와 X2가 Y의 값을 결정할 때 이를 완전 함수적 종속이라고 하고, X1, X2 중 하나만 Y의 값을 결정할 때 이를 부분 함수적 종속 이라고 한다.
-> 만약 X가 기본키이고, X->Y / Y->Z의 함수적 종속 관계가 있어서 Y와 Z를 별도의 테이블로 만들면 제3정규형이 됨

#### 제 3 정규화
- 기본키가 아닌 모든 속성들이 기본키에 이행적 함수 종속이 아님
-> 만약 X,Y가 기본키이고 X,Y->Z / Z->Y의 함수적 종속 관계가 있는 경우, X,Z / Z->Y 의 테이블을 따로 구성하면 BCNF 정규형이 됨

#### BCNF 정규화
- 테이블 내의 모든 결정자(함수적 종속에서 X에 해당)가 후보키임  
<a href="#top">TOP</a>

### 인덱스를 사용하는 이유와 장단점

### ORM

### NoSQL
#### 기본적인 특징
- 배경: 패러다임 쉬프트 (한정된 복잡성 높은 데이터 -> 단순한 대량의 데이터)
- 특징
  - 데이터 간의 관계를 정의하지 않음. 따라서 테이블 간의 join도 불가능함
  - 대부분 분산형 구조로 구성. 데이터 상호 복제
  - 유동적인 테이블 스키마
  - CAP 이론: Consistency, Availability, Partitioning 중 2가지만 만족 가능. 따라서 트랜잭션 제공 X
  - 확장성, 가용성, 높은 성능
- 형태
  - Key Value DB: unique한 key가 하나의 value를 가짐
  - Wide Comlumnar Store: Column Family 데이터 모델 사용 (HBase, Cassandra, ScyllaDB 등)
  - Document DB: JSON, XML과 같은 Collection 데이터 모델 사용 (MongoDB, CoughDB 등)
  - Graph DB: Nodes, Relationship, Key-Value 데이터 모델 채택 (Neo4J, OreientDB 등)
- NoSQL은 기존 RDBMS로 생각하면 아래의 두 기능만 제공함
  - Put: Insert into TABLE VALUES(KEY, value1, value2, value3, ... valuen)
  - Get: Select * from TABLE where KEY ="key"
  - 따라서 Order By, Join, Group By, Range Query, Index 등을 어떻게 구현할지 생각해야 함
- 수행할 쿼리를 정의하고 이에 맞춰 테이블을 정의함. 따라서 데이터 모델링이 설계의 90%에 해당함  

#### 종류: HBase/ Cassandra/ MongoDB
<a href="#top">TOP</a>