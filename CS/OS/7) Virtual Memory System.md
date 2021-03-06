## 가상 메모리 기초
### 기본 개념
- 메모리 용량 제한 문제 때문에 각 프로세스가 현재 사용하는 공간만 물리적인 메모리에 저장하는 방식이 필요함
- 기본 아이디어: 전체 프로세스는 가장 주소를 참조하며 사용 중인 부분만 물리 주소에 저장. 실제 해당 주소에서 데이터를 읽고 쓸때만 가상 주소를 물리 주소로 바꿔주면된다.
- CPU는 가상 메모리를 다루고, 실제 해당 주소 접근시 MMU를 통해 물리 메모리에 접근
- MMU (Memory Management Unit): 가상 주소를 물리 주소값으로 빠르게 변환해주는 하드웨어 장치
- 기본적으로는 프로세스 당 4GB가 할당됨

### 페이징 시스템
- 페이징: 가상 메모리 공간을 페이지 단위로 나누고, 페이지 별로 물리 메모리에 저장 (리눅스에서는 4KB)
- Page Table: 페이지 번호 별로 가상 주소와 물리 주소를 mapping 해놓은 것 (실제 물리 주소에 있는지를 나타내는 valid 정보도 있음). 프로세스의 PCB에는 Page Table의 주소가 있고 Page Table의 정보는 물리 메모리에 저장됨. 프로세스 구동시 CR3라는 레지스터에 별도로 page table의 base 주소가 저장됨
- 가상 주소 (32bit) = (p, d)
  - p: 가상 메모리 페이지
  - d: p 안에서 참조하는 위치 (변위)
- paging system 동작: 해당 프로세스의 page table에 원하는 page 번호가 있는지 확인 -> 있는 경우 page가 mapping된 첫 물리 주소를 알아냄(p') -> p'+d가 실제 물리 주소가 됨
- 공유 메모리: 둘 이상의 프로세스가 공유하는 부분을 동일한 물리 주소에 저장 (Page Table에서 동일 주소를 참조. 예를 들어 커널 영역 부분)
- 내부 단편화: 페이지 블록만큼 데이터가 채워지지 않을 경우 메모리 공간이 낭비됨

#### 다중 단계 페이징 시스템
- 페이징 정보를 단계를 나누어 생성
- 가상 메모리의 나머지 필요없는 영역들은 페이지를 생성하지 않아서 공간이 절약됨 
- 페이지 번호를 나타내는 bit 중 변위 12bit를 제외하고, 앞의 20bit를 나누어서 단계를 나눔

#### MMU와 TLB
- 실제 메모리 접근 방식: CPU 특정 프로세스의 가상 메모리 주소 요청 -> MMU가 CR3 레지스터 값에 접근하여 해당 프로세스의 page table에 접근 -> 해당 데이터의 물리 주소 가져옴 -> MMU가 해당 데이터의 물리 주소에 접근 -> 메모리가 해당 데이터를 CPU에 전달
- TLB(Translation Lookaside Buffer): MMU가 한번 받아온 물리 주소를 캐쉬로 저장 -> 다음번에 같은 주소 요청시 TLB에서 해당 주소를 제공 -> 해당 데이터의 물리 주소 접근 후 CPU에 전달

#### 요구 페이징
- 프로세스 실행 중에 특정한 시점에 필요한 데이터의 페이지를 메모리에 적재 (lazy allocation)
- 페이지 폴트가 발생하여 해당 페이지가 물리 메모리에 올라감
- 반대) 선행 페이징: 프로세스 관련 모든 데이터를 미리 메모리에 올려놓고 실행

#### 페이지 폴트 (interrupt)
- 요청된 어떤 페이지가 물리주소에 없는 상황에서 발생하는 인터럽트 -> OS는 페이지 폴트 인터럽트에 해당하는 OS 함수 실행 -> 저장매체에 접근하여 페이지를 메모리에 올림
- 자주 일어나면 실행 시간이 길어짐
- Thrashing: 과도하게 페이지 폴트 및 교체가 발생해서 실제로는 아무런 CPU 작업이 일어나지 못하는 상황
<br>
<img src=https://user-images.githubusercontent.com/65876994/93206638-b14c2780-f794-11ea-8498-9333f1740239.PNG>

#### 페이지 교체 정책
- 물리 메모리가 다 차있는 경우 새로운 페이지를 올리기 위해 어떤 페이지를 내릴 것인가?
- FIFO 알고리즘
- 최적 페이지 교체 알고리즘 (OPT): 앞으로 가장 오래 사용하지 않을 페이지를 내리는 정책 (일반 OS에서는 구현 불가)
- Least Recently Used(LRU): 가장 오래 전에 사용된 페이지를 교체 (과거 기록을 이용한 구현). 가장 많이 사용됨
- Least Frequently Used(LFU): 가장 적게 사용된 페이지를 교체
- Not Used Recently(NUR): 각 페이지마다 참조 비트(R), 수정 비트(M)을 기록 -> 앞에서부터 (0,0)/(0,1)/(1,0)/(1,1) 순으로 페이지 교체

### 세그멘테이션 기법
- 가상 메모리를 서로 크기가 다른 논리적 단위인 Segment로 분할
- 페이징 기법에서는 같은 크기의 블록으로 분할
- 가상 주소에 대응되는 물리 주소 계산방식은 페이징 기법과 거의 동일 (세그먼트 번호, 변위)
