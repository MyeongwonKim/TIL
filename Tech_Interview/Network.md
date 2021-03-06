## 네트워크
- 기본 개념
- OSI 7계층
- HTTP
  - 기본 개념
  - http/https
  - HTTP 버전
  - GET 방식과 POST 방식의 차이
- REST API의 개념
- Session과 Cookie
<br><br><br>

### 기본 개념
- 패킷: 네트워크 통신 시 사용되는 작게 분할된 데이터 조각. 데이터 전송의 기본 단위
- LAN: 건물 안이나 특정 지역을 범위로 하는 좁은 범위의 네트워크. 속도가 빠르며 오류가 발생할 확률이 낮다.
- WAN: ISP(인터넷 서비스 제공자)가 제공하는 서비스를 사용하여 LAN들을 다시 하나로 묶는 넓은 범위의 네트워크. LAN에 비해 속도가 느리고 오류가 발생할 확률이 높다.
- DMZ: 일반적으로 인터넷인 외부 네트워크와 내부 네트워크 사이에 위치한 중간 지대를 말함. 외부에 서버를 공개할 목적으로 회사에서 사용되는 네트워크

### OSI 7계층
- 네트워크의 기본 구조를 일곱개의 계층으로 나눠서 표준화한 국제 표준 통신 규약
- 7계층: 물리/ 데이터 링크/ 네트워크/ 전송/ 세션/ 표현/ 응용
- cf) TCP/IP 모델: OSI 7 계층을 네 개의 계층 (네트워크 접속, 인터넷, 전송, 응용)으로 단순화하여 사용하는 모델  
<a href="#top">TOP</a>

#### 7계층 상세요약
- 물리 계층 (Physical Layer): 시스템 간의 물리적인 연결과 전기 신호의 변환 및 제어를 담당한다.
  - 0과 1의 비트열로 된 데이터가 랜 카드에서 전기 신호로 변환된 후 유무선 전송 매체를 통해 전송이 일어난다.
- 데이터 링크 계층 (Data Link Layer): LAN에서 네트워크 기기 간의 데이터 전송을 담당한다.
  - 이 계층에서는 전달된 데이터에 이더넷 헤더(목적지 MAC 주소, 출발지 MAC 주소, 유형)와 트레일러를 붙임 -> 데이터는 프레임이 됨 -> 물리 계층에서 이 프레임 비트열을 전기 신호로 변환하여 네트워크를 통해 전송함
  - 이더넷: LAN에서 데이터를 주고받기 위한 규칙. 데이터 충돌을 막기 위해 CSMA/CD 방식을 사용함
  - MAC 주소: 랜 카드 제조시 정해지는 컴퓨터의 물리 주소로 48비트의 숫자 (16진수)로 구성됨
  - 스위치: LAN을 구성할 때 사용하는 단말기 간 스위칭 기능이 있는 통신망 중계 장치
    - MAC 주소 테이블: 스위치의 port number와 해당 port에 연결된 컴퓨터의 MAC 주소가 등록되어 있는 데이터 베이스
  - ARP (Address Resolution Protocol): 목적지 컴퓨터의 IP 주소를 이용하여 물리 주소인 MAC 주소를 찾기 위한 프로토콜 
    - ARP 요청: 목적지 컴퓨터의 MAC 주소를 모를 경우 네트워크에 브로드캐스트를 하여 지정된 IP 주소를 가진 컴퓨터로부터 MAC 주소를 얻어와서 이더넷 프레임을 만든다.
    - ARP 테이블: MAC 주소와 IP 주소의 매핑 정보를 보관한 테이블. IP 주소가 변경될 수 있으므로 일정 기간이 지나면 삭제 후 다시 ARP 요청을 함
- 네트워크 계층 (Network Layer): 네트워크 간의 통신을 하기 위한 경로 설정 및 논리 주소를 결정한다.
  - 라우터: 네트워크 간의 통신이 가능하게 하는 중계 장치로서 해당 목적지까지 가는 최적의 경로를 찾는 라우팅 기능을 담당함
  - IP: 네트워크 정보를 포함한 컴퓨터의 논리 주소. 네트워크 계층에서 캡슐화시 데이터에 IP 헤더를 붙임
    - IP 헤더: 출발지 IP, 목적지 IP, 버전, 헤더 길이, 헤더 체크섬 등이 포함됨 -> 데이터는 IP 패킷이 됨
    - IPv4: 현재 주로 사용하는 IP 버전으로서 32비트이며 10진수 4자리로 표현함. 약 43억개의 주소를 생성할 수 있음
      - ex) 192.168.1.1/16: 서브넷 마스크는 255.255.0.0이고 앞의 16비트가 네트워크 부, 나머지 16비트가 호스트부임
    - IPv6: 128비트로 구성된 IP 주소로서 IPv4의 개수 부족을 해결하기 위한 주소임
  - 서브넷 마스크: IP 주소에서 네트워크 주소와 호스트 주소를 식별하기 위한 값
    - 서브넷팅: 네트워크를 쪼개서 브로드캐스트 시 패킷을 전송해야 하는 범위를 줄임. 이를 통해 네트워크 통신 성능을 향상시킴
    - IP 주소 A클래스, B클래스, C클래스에 해당하는 기본 서브넷 마스크가 존재함 (각각 8, 16, 24개의 1로 구성됨)
    - 서브넷 마스크를 이용하면 비트 AND 연산을 통해 해당 IP의 네트워크 주소를 알아낼 수 있음
    - 사용자가 네트워크 부분을 늘리기 위해 C클래스 IP 주소에 28비트의 서브넷 마스크를 지정할 수 있음. 이를 통해 나머지 4개의 비트로 호스트 IP를 지정할 수 있는 서브넷이 구성됨. 서브넷도 하나의 독립적인 네트워크로서 라우터를 통해 통신이 이루어져야 함
- 전송 계층 (Transport Layer): 네트워크 간의 통신 시 신뢰할 수 있는 통신을 구현하고, 목적지 컴퓨터의 어떤 애플리케이션에 데이터를 전송할지 결정
  - TCP (Transmission Control Protocol): 연결형 통신 프로토콜. 신뢰성과 정확성을 우선시 하여 수신측의 응답을 확인하며 통신하는 방식. 데이터 누락 방지
    - TCP 헤더: TCP로 전송시 붙이는 헤더. 출발지 및 목적지 포트 번호, 일련 번호, 확인 응답 번호, 코드 비트(SYN, ACK, FIN 등) 등 포함. 데이터 -> 세그먼트
    - Connection: TCP 통신에서 데이터 전송을 위해 사용되는 가상의 통신로
    - 3-way-handshake: connection 확립을 위한 패킷 교환. 송신: 연결 요청(SYN) -> 수신: 확인 응답(ACK) + SYN -> 송신: ACK
    - 이후 실제 데이터 전송 시에는 일련번호와 확인 응답 번호를 확인하며 세그먼트가 전달된다.
    - 일련번호: 송신 측이 수신 측에 해당 데이터가 몇 번째 데이터인지를 알려줌
    - 확인 응답 번호: 수신 측이 몇 번째 데이터를 수신했는지 송신 측에 알려줌
    - 윈도우 크기만큼의 세그먼트들을 한번에 받고, 그에 해당하는 확인 응답들을 한번에 할 수 있다. 
  - UDP (User Datagram Protocol): 비연결형 통신 프로토콜. 효율성과 속도를 우선시하여 일방적으로 데이터를 전송하는 방식
    - UDP 헤더: 출발지 및 목적지 포트 번호, 길이, 체크섬을 포함함. 데이터 -> UDP 데이터그램
    - 네트워크 내에서 빠른 속도로 브로드캐스트 방식 데이터 전송시 사용
- 세션 계층 (Session Layer): Connection과 관련하여 세션 체결, 통신 방식을 결정한다. 응용 계층에 포함됨
- 표현 계층 (Presentation Layer): 문자 코드, 압축, 암호화 등의 데이터를 통신에 적합한 방식으로 변환한다. 응용 계층에 포함됨
- 응용 계층 (Application Layer): 이메일, 파일 전송, 웹 사이트 조회 등 애플리케이션에 대한 서비스를 제공한다.
  - 각각의 애플리케이션에 대응되는 데이터를 전송하는 역할을 수행함
  - HTTP, FTP, SMTP(메일 송신), POP3(메일 수신) 등의 포로토콜에 따라 데이터 전송이 수행됨
  - DNS: URL을 IP 주소로 변환해주는 시스템. DNS 서버가 이 기능을 담당함. 이렇게 얻은 IP를 가지고 웹 서버에 접속한다.  
<a href="#top">TOP</a>

### HTTP

#### 기본 개념
- HTTP: 웹 서비스에서 클라이언트와 웹 서버 간에 정보를 주고받기 위해 사용되는 네트워크 프로토콜
- WWW: HyperText들을 URL을 통해 연결시켜서 형성된 정보 공유를 위한 가상의 공간
- URI와 URL: URI(Uniform Resource Identifier)는 인터넷 상의 자원을 식별하기 위한 문자열임. URL(Uniform Resource Locator)은 URI의 한 형태로서 인터넷 상의 자원 위치를 의미한다. URL은 결국 웹 상에서 서비스를 제공하는 각 서버에 있는 '하나의 파일 위치'를 나타내는 것. 하지만 검색엔진에서 검색한 결과와 같이 동적으로 생성된 정보는 URL로 표현할 수 없다. 이런 경우 URL에 query string이 포함되는데, 이것은 URI라고 할 수 있다 (URI: 파일의 위치 + 전달된 인자)

#### http/https

#### HTTP 버전
- HTTP/1.0: 요청을 보낼 때마다 connection을 수립하고 응답 후 coonection을 끊음
- HTTP/1.1: connction 한번 수립 후 데이터 교환이 끝나면 connection을 끊는 것이 가능 (keepalive 기능)
- HTTP/2: 요청을 보낸 순서와 상관없이 응답이 가능하여 빠르게 콘텐츠를 표시할 수 있음  
<a href="#top">TOP</a>

 #### GET 방식과 POST 방식의 차이
 1) Get 방식
- 클라이언트에서 서버로 어떤 리소스를 요청하기 위한 HTTP 메서드
- ex) 게시판의 게시글 조회
- 요청 url 끝에 parameter인 query string을 포함하여 전송 (HTTP 요청 메시지에 body가 없음)
- 캐시가 가능함. 해당 리소스의 웹 캐시가 있는 경우 서버에 접근하지 않고 리소스의 복사본을 반환함
- query string이 다 보여지므로 보안성이 떨어짐/ 전송 데이터의 길이 제한이 있음
2) Post 방식
- 클라이언트에서 서버로 리소스 생성이나 업데이트를 하기 위한 데이터 전송시 사용되는 HTTP 메서드 
- ex) 게시판에 게시글 작성
- 전송할 데이터를 HTTP 메시지 Body에 담아서 전송
- POST 요청은 캐시되지 않음. 전송 데이터의 길이 제한 없음
- GET과 같이 데이터가 외부로 드러나지 않으므로 보안성이 더 좋음 (그러나 데이터가 암호화되지 않으면 body의 데이터도 볼 수 있음)  
<a href="#top">TOP</a>

### REST API의 개념

#### REST API의 개념  
Representational State Transfer. "웹에 존재하는 모든 자원(이미지, 동영상, DB 자원)에 고유한 URI를 부여해 활용하는 것"으로, 자원을 정의하고 자원에 대한 주소를 지정하는 방법론을 의미함. RESTful API는 REST의 특징을 지키면서 API를 제공하는 것을 의미한다. 2000년도에 로이 필딩 (Roy Fielding)의 박사학위 논문에서 최초로 소개. 로이 필딩은 HTTP의 주요 저자 중 한 사람으로서 웹의 장점을 최대한 활용할 수 있는 아키텍처로써 REST를 발표함

#### REST API의 구성  
1) 리소스 도입 (URI)
- 클라이언트의 요청을 단일 서비스 endpoint로 보내는 것이 아니라 개별 리소스로 통신을 요청한다. URI에 요청이나 파라미터를 명시한다.
2) HTTP METHOD 도입
- URI + HTTP Method의 조합으로 리소스를 구분함
- 발생한 에러에 대한 커뮤니케이션을 위해 HTTP Status code를 사용함. 서버는 순수하게 API로서의 기능만 제공하며 View는 클라이언트에서 전적으로 담당하여 표시함
3) hypermedia control
- 하이퍼미디어: 하나의 컨텐츠가 텍스트나 이미지, 사운드와 같은 다양한 포맷의 컨텐츠를 링크하는 개념
- 클라이언트가 특정 API를 요청하면, 서버에서 다음 단계의 작업을 위한 리소스 URI를 알려주는 것임
- 클라이언트가 수행할 작업을 찾기 위해 링크를 따라가기 때문에 컨트롤 탐색에 꽤 많은 호출이 발생할 수 있으며 이에 따라 복잡성이 증가한다. 그러나 서비스 대 서비스의 상호작용을 위해선 필요한 단계이다.

#### REST API의 6가지 특징
1) Uniform Interface
- HTTP 표준만 따른다면, 어떠한 기술이든 사용이 가능한 인터페이스임
- 예를 들어서 HTTP + JSON으로 REST API를 정의했다면, 안드로이드 플랫폼이건, IOS 플랫폼이건, C / Java / Python 특정 언어나 기술에 종속 받지 않고 HTTP와 JSON을 사용할 수 있는 모든 플랫폼에 사용이 가능한 느슨한 결합(Loosely Coupling) 형태의 구조임
2) Client-Server
- 클라이언트는 연결자를 통해 서버에 요청을 보내며, 서버는 해당 요청을 거절하거나 수행하고 클라이언트에 응답을 보낸다. 그리고 모든 통신은 클라이언트-서버 간의 일대일로 연결된다.  
- 서버는 API를 제공하고, 제공된 API를 이용해서 비즈니스 로직 처리 및 저장을 책임진다. 클라이언트의 경우 사용자 인증이나 Context(세션, 로그인 정보)등을 직접 관리하고 책임 지는 구조로 역할이 나뉘어 지고 있다. 
- 역할이 각각 확실하게 구분되면서, 클라이언트와 서버에서 개발해야 할 내용들이 명확하게 되고 서로의 개발에 있어서 의존성이 줄어들게 된다.
3) Stateless
- 클라이언트의 context (session과 같은 상태 정보)를 클라이언트가 가지고 있고 서버 쪽에 유지하지 않는다는 의미. 각 API 서버는 들어오는 요청의 메시지만 가지고 로직을 처리하기 때문에 구현이 단순해진다.
4) Cacheable
- HTTP라는 기존의 웹 표준을 그대로 사용하기 때문에 캐싱 기능을 적용할 수 있다.
- 구현은 HTTP 프로토콜 표준에서 사용하는 "Last-Modified" 태그나 E-Tag를 이용하면 캐싱을 구현할 수 있다.
- 캐시를 사용하게 되면 네트워크 응답시간 뿐만 아니라, REST 컴포넌트가 위치한 서버에 트랜잭션을 발생시키지 않기 때문에, 전체 응답시간과 성능 그리고 서버의 자원 사용률을 비약적으로 향상시킬 수 있다.
5) Layered System
- 순수 비즈니스 로직을 수행하는 API 서버와 그 앞 단에 사용자 인증, 암호화, 로드밸런싱 등을 하는 계층을 추가하여 구조상의 유연성을 둘 수 있음
6) Code on Demand (optional)
- 클라이언트는 리소스에 대한 표현을 응답으로 받고 처리해야 하는데, 어떻게 처리해야 하는지에 대한 Code를 서버가 제공하는 것을 의미한다. Html에서의 javascript가 가장 대표적인 예이다. 

#### REST API 디자인 가이드
1) URI는 정보의 자원을 표현해야 한다. 여기에 행위에 대한 표현이 들어가서는 안된다.
2) 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현한다.

#### 장단점
- 장점: Open API를 제공하기 쉽다. 원하는 타입으로 데이터를 주고 받을 수 있다. 기존 웹 인프라 (HTTP)를 그대로 사용할 수 있다.
- 단점: 사용할 수 있는 메서드가 4가지 밖에 없다. 분산환경에서는 부적합하다. HTTP 통신 모델에 대해서만 지원한다.

#### [참조 링크](https://noahlogs.tistory.com/47?category=827412)  
<a href="#top">TOP</a>

### Session과 Cookie
1) 사용 이유
- HTTP프로토콜은 새로운 페이지를 요청할 때마다 새로운 접속이 이루어지며 이전 페이지와 현재 페이지 간의 관계가 지속되지 않는다. 이에 따라 웹사이트에서는 특정 방문자가 머무르고 있는 동안에 그 방문자의 상태를 지속시키기 위해 쿠키와 세션을 이용한다.
2) Session
- 클라이언트가 웹서버에 연결된 순간부터 웹 브라우저를 닫아 HTTP 통신이 끝날 때까지의 기간
- 최초 요청시 서버에서 session 객체를 생성하고 각 클라이언트 마다 고유한 session ID 값을 부여함
- 쿠키를 사용하여 session ID 값을 클라이언트에 보냄
- **결국 세션은 쿠키를 이용하는 하나의 방식일 뿐 반대의 개념이 아님**
- 브라우저가 닫히거나 서버에서 삭제 시 사라짐
3) Cookie
- 서버가 사용자의 웹 브라우저에 전송하는 작은 데이터 조각. 웹 사이트에 접속한 클라이언트의 확인을 위해 만들어짐
- 요청을 받은 서버에서 쿠키를 HTTP header에 담아서 클라이언트로 보냄 -> 클라이언트는 쿠키 디렉토리에 domain name 별로 쿠키를 저장함 -> 이후 클라이언트가 동일 서버로 HTTP 요청을 보내면 저장된 쿠키도 함께 전송됨
- 서버가 아닌 Client PC에 텍스트 파일로 저장됨. 기본적으로는 웹 브라우저 종료시 삭제됨
- 클라이언트의 쿠키를 확인할 경우 정보 유출의 가능성이 있음 -> **중요한 정보는 서버의 세션에서 관리하고, 클라이언트에게 session ID를 주어 식별 가능하도록 함**
- 모든 정보를 서버 내의 세션에 저장할 경우 과부하가 걸리기 때문에 중요하지 않은 설정 정보 등은 쿠키에 저장  
<a href="#top">TOP</a>
