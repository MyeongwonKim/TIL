### Inversion of Control
- 일반적인 의존성 제어권: "내가 사용할 의존성은 내가 만듦"
- IoC: "내가 사용할 의존성을 누군가 알아서 주겠지"
- 의존성을 직접 객체 생성하지 않고, 생성자에서 매개변수로 받아서 사용

#### 예시
```java
class OwnerController {
   private OwnerRepository repo;

   public OwnerController(OwnerRepository repo) {
       this.repo = repo;
   } 

}

class OwnerControllerTest {
   @Test
   public void create() {
         OwnerRepository repo = new OwnerRepository();
         OwnerController controller = new OwnerController(repo);
   }
}
```
- [참고 링크](https://martinfowler.com/articles/injection.html)

### IoC 컨테이너
- Spring이 사용하는 dependency 컨테이너: XML이나 Annotation 명세를 따라 Spring이 bean 객체를 생성하고 구조적으로 조립한 뒤 자동으로 DI를 수행
- 구현 interface: BeanFactory (그 하위 interface인 Application Context를 주로 사용)
- 저장된 bean을 특정 생성자가 매개변수로 받아서 의존성으로 사용할 시 자동으로 제공해준다.  
  (즉 applicationContext.getBean(해당 bean의 class type)을 통해 수동으로 꺼낼 필요가 없음)
- IoC 컨테이너 내에 저장된 bean끼리만 의존성 주입이 가능하다.
- 싱글톤 scope을 손쉽게 달성할 수 있다.

### Bean
- IoC 컨테이너가 관리하는 객체
- 특정 클래스를 생성자로 만든 객체와 applicationContext.getBean()으로 가져온 객체(bean)는 다름
- 특정 클래스를 bean으로 등록하는 방법
  - @Component annotation 사용 (@Component, @Repository, @Service, @Controller, @Configuration) -> SpringBoot가 ComponentScan 기능을 통해 자동으로 Bean으로 등록해줌
  - 직접 xml이나 자바 설정 파일(@Configuration을 가진 class)에 하나하나 등록
  - 특정한 interface를 상속
- 꺼내 쓰는 방법
  - @Autowired 또는 @Inject
  - 또는 ApplicationContext에서 getBean()으로 직접 꺼내서 씀

### 의존성 주입 (@Autowired / @Inject)
- Field에 annotation 추가 (이 경우 final 지시자는 쓰면 안 됨)
- 생성자에 annotation 추가 (Spring 4.3부터는 annotation 없이도 자동 주입)
  - 의존성이 있어야만 해당 클래스를 객체로 생성하도록 강제하는 장점이 있음
- Setter를 만들고 annotation을 추가
