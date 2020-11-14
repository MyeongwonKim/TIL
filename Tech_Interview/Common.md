## 언어 공통 및 기술 일반
- TDD
- 웹 페이지 요청시 일어나는 일련의 과정
- 로드밸런싱과 클러스터링
- 프록시 서버
- 컴파일러와 인터프리터
- 어셈블리어
- C와 Java의 차이
- 자바와 자바스크립트 차이
- 프레임워크와 라이브러리
- MVC 패턴
- Web Server와 WAS
- Servlet과 JSP
- Maven과 Gradle의 차이
- 싱글톤패턴
- DAO, DTO
- call by value와 call by reference의 차이

### TDD
- 테스트가 프로그램의 코드 작성을 주도하는 개발 방식. 업무 코드 작성 전에 테스트 코드를 먼저 만드는 것
- 새로운 기능에 대한 자동화된 테스트케이스를 먼저 작성하고 해당 테스트를 통과하는 가장 간단한 코드를 작성한다. 그 후 상황에 맞게 리팩토링하는 과정을 거침
- 장점
  - 테스트 코드를 작성하면서 설계의 구조적인 문제들을 바로 찾아내고 수정하게 됨
  - 단위 테스트 기반의 테스트 코드 작성시 각 모듈별 테스트를 통해 문제의 지점을 쉽게 찾아낼 수 있음
- TDD 개발의 3단계: 실패하는 작은 테스트 케이스 작성 -> 이를 통과하는 코드 작성 -> 작성한 코드에서 모든 중복을 제거하고 간결하게 수정한다.  
<a href="#top">TOP</a>

### 웹 페이지 요청시 일어나는 일련의 과정

### 로드밸런싱과 클러스터링
- 로드밸런싱: 부하 분산을 위해서 가상 IP를 통해 여러 서버에 접속하도록 분배하는 기능. 대용량 트래픽을 장애없이 처리하려면 여러 대의 서버에 적절히 트래픽을 분배해야 한다. 로드밸런서는 동시에 오는 수많은 커넥션을 처리하고 해당 커넥션을 요청 노드 중 하나로 전달될 수 있게 한다. 로드밸런서에서 서비스 요청을 처리하는 방법에는 다양한 알고리즘이 있다. 랜덤, 라운드 로빈, CPU나 메모리 사용률 등과 같은 특정 범주에 따라 노드를 선택하는 등의 방법이 있다.
- 클러스터링: 여러 대의 컴퓨터를 연결한 병렬 시스템을 마치 하나의 컴퓨터처럼 사용하는 것. 특정 장비에 문제가 생겨도 전체 서비스에 영향을 미치지 않도록 제어가 가능함  
<a href="#top">TOP</a>

### 프록시 서버
- 개념: 클라이언트의 요청을 받아서 대신 서버에 접근하고 자원을 받아서 전달해주는 중계 서버
- 기능
  - 캐싱: 클라이언트로부터 요청된 자원들을 캐시로 저장하고 제공함으로써 데이터 전송 시간을 줄여주고 서버 측의 네트워크 병목 현상을 방지해줌
  - 보안: 방화벽과 웹 필터로서 작동함. 위험이 예상되는 웹 콘텐츠 및 악성코드를 필터링함. 또한 서버 상의 특정 웹 페이지들에 대한 접근을 제한함
  - privacy: 클라이언트의 정보를 감추는 역할을 하기도 함. 웹 요청시에 클라이언트의 IP를 변경하거나 클라이언트의 data를 암호화함으로써 이 기능을 수행함  
<a href="#top">TOP</a>

### 컴파일러와 인터프리터
- 컴파일러: 고급 언어로 작성된 프로그램 전체를 목적 프로그램 (기계어)으로 번역한 후, 링킹 작업을 통해 컴퓨터에서 실행 가능한 실행 프로그램을 생성한다. 번역 시간이 오래 걸리지만 한번 번역한 후에는 다시 번역하지 않으므로 실행 속도가 빠르다. 컴파일러를 사용하는 언어에는 C언어 Java 등이 있음
- 인터프리터: 고급 언어로 작성된 프로그램을 한 줄 단위로 받아들여 번역하고 동시에 한 줄 단위로 실행시키는 프로그램. 따라서 목적 프로그램이 따로 생성되지 않기 때문에 코드가 수정되어도 (컴파일 과정 없이) 바로 실행 가능함. 번역 속도는 빠르지만 프로그램 실행 시 매번 번역해야 하므로 실행 속도는 느림. 대표적인 인터프리터 언어로 python이 있음  
<a href="#top">TOP</a>

### 어셈블리어
- 명령 기능을 쉽게 연상할 수 있는 기호를 기계어와 1:1로 대응시켜 코드화한 저급 언어. 어셈블리어로 작성한 원시 프로그램은 어셈블러를 통해 목적 프로그램(기계어)으로 어셈블 하는 과정을 거쳐야함. 컴퓨터 구조에 따라 사용하는 기계어가 달라지며, 따라서 기계어에 대응되어 만들어지는 어셈블리어도 각각 다르게 된다.

### C와 Java의 차이


### 자바와 자바스크립트 차이
#### 자바스크립트
- JS의 탄생 목적은 어떤 프로그램을 만들기 위한 것이 아닌 웹 페이지의 동적 제어였음. 초기에는 인터프리터에 의해 실행되었으나 현재는 JIT 컴파일에 의해 실행됨
- Just-In-Time 컴파일: dynamic compile이라고도 불리며 프로그램 실행 시점에 전체 코드에서 필요한 부분을 기계어로 번역하고 실행하는 기법. 컴파일 방식과 인터프리터 방식을 혼합한 방식임. 실행 시점에 인터프리트 방식으로 기계어 코드를 생성하면서 그 코드를 캐싱하여, 같은 함수 재사용시 매번 기계어 코드가 생성되는 것을 방지한다. 
- 동적 type: 기본적으로 변수를 만들 때 변수의 type을 지정하지 않기 때문에 담기는 값에 따라 type이 결정되며 재할당에 의해 type이 바뀔 수도 있음
- JS의 함수는 1급 객체임: 함수를 변수에 담을 수 있고, 인자로 전달할 수 있으며, 반환값으로 전달할 수 있다. 이로 인한 장점은 each, filter, map, sort와 같은 고차 함수를 사용할 수 있다는 것임  
<a href="#top">TOP</a>

### 프레임워크와 라이브러리
#### 프레임워크
- 소프트웨어의 구체적인 기능들에 해당하는 설계와 구현을 제공하는 소프트웨어 환경. 일련의 협업화된 형태의 클래스와 인터페이스들의 집합으로 제공된다.
- 애플리케이션의 틀과 구조를 결정할 뿐 아니라, 그 위에 개발된 개발자의 코드를 제어한다. 이를 통해 전체 코드의 통합성과 체계성을 유지한다. 차후 안정적인 유지보수를 가능하게 한다.
- 구체적이며 확장 가능한 기반 코드를 가지고 있으며, 설계자가 의도하는 여러 디자인 패턴의 집합으로 구성되어 있다. 개발자로하여금 구체적인 기능 구현에만 전념하도록 해줌
- 다양한 라이브러리, DB 접근 interface, configuration 등을 포함함 (spring, django, hibernate, Mybatis, ... )
- spring frame
- 라이브러리와의 차이점: 기능을 정의하는 클래스들(라이브러리)과 클래스들 간의 관계로 이루어진 설계의 기본 틀(확장 가능한 기반 코드로 제시됨)을 제공한다.
#### 라이브러리
- 자주 쓰이는 독립성을 지닌 기능들을 모아 놓은 클래스들의 모음집. 개발자가 구현하고자하는 기능을 쉽게 제공해주는 중간 계층
- 라이브러리는 필요한 기능을 제공하지만, 그 기능을 쓰기 위한 구조를 제공하지는 않는다.
- 언어별 표준 라이브러리 / 사용자가 직접 정의한 라이브러리 (다양한 오픈 소스 라이브러리 포함)
<a href="#top">TOP</a>

### MVC 패턴
- 객체 지향 프로그래밍에서 사용자 인터페이스를 효과적으로 데이터 모형에 관련시키기 위한 설계 방식 중 하나
- Model: 논리적 데이터 기반 구조를 표현. db와의 연결을 담당함. 사용자 인터페이스와 관련된 정보는 갖고 있지 않음
- View: 사용자 인터페이스 내의 구성요소들을 표현 (사용자에게 보여지는 화면)
- Controller: 사용자의 입력 처리와 흐름제어를 담당함. Model과 View 내의 클래스들 간의 정보 교환을 위해 사용됨  
<a href="#top">TOP</a>

### Web Server와 WAS
- Web Server: 웹 상에서 클라이언트의 HTTP 요청을 받아서 이에 해당하는 경로의 정적인 컨텐츠를 제공하는 서버. image, html, css, javascript와 같은 파일들을 반환함
- WAS: HTTP 요청에 포함된 인자 값에 맞는 동적인 컨텐츠를 제공하는 서버. DB 조회나 다양한 로직 처리를 수행한 뒤 동적으로 생성된 페이지를 반환함

### Servlet과 JSP
<a href="#top">TOP</a>

### Maven과 Gradle의 차이
- Maven: Maven은 내가 사용할 라이브러리 뿐만 아니라 해당 라이브러리가 작동하는데 필요한 다른 라이브러리들까지 관리하여 네트워크를 통해 자동으로 다운 받아준다. 또한 Maven은 프로젝트의 전체적인 라이프사이클을 관리하는 도구이며, 많은 편리함과 이점이 있어 널리 사용되고 있다.
- Gradle: Ant와 Maven의 장점을 모아서 출시된 build tool. 안드로이드 앱의 공식 빌드시스템이기도 하며 JAVA, C/C++, Python 등을 지원한다. Maven의 경우 XML로 라이브러리를 정의하고 활용하도록 되어 있으나, Gradle의 경우 빌드스크립트가 groovy 언어로 작성되어 사용할 어플리케이션 버전, 라이브러리등의 항목을 설정 할 수 있다.
  - 장점: Maven에 비해 설정 내용이 간결하고 가독성이 있음

### 싱글톤패턴
https://jeong-pro.tistory.com/86?category=793347

### DAO, DTO
### call by value와 call by reference의 차이