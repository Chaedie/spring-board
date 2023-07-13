### 트러블 슈팅

#### 게시글 작성 시 게시글 당 여러 파일 저장이 어려움 (JPA Mapping 개념 부족)

> 23.07.12. 20:09

- 이를 해결하기 위해 우선 한 게시글 당 하나의 파일만 저장되도록 구현할 예정
- 이후 SPRING 강의와 JPA 학습 후 다시 작성해볼 예정

> 23.07.13.

- `<자바 ORM 표준 JPA 프로그래밍>`에 아래와 같은 내용이 나왔다.
- 데이터 중심 설계의 문제점
    - 이 예제의 엔티티 설계가 이상하다는 생각이 들면 객체지향 설계를 의식하는 개발자
    - 자연스러웠다면 데이터 중심의 개발자일 것이다.
    - → 이 방식은 객체 설계를 테이블 설계에 맞춘 방법이다. 특히 테이블의 외래 키를 객체에 그대로 가져온 부분이 문제다. 왜냐면 RDB는 연관 객체를 찾을 때 외래 키를 사용해서 조인하면 되/지만 객체에는
      조인이라는 기능이 없다. “객체는 연관된 객체를 찾을 때 참조를 사용해야 한다.”

    ```java
    // Join 방식
    Order order = em.find(Order.class, orderId);
        
    //외래 키로 다시 조회
    Member member = em.find(Member.class, order.getMemberId());
    ```

    ```java
    // 객체 참조 방식
    Order order = em.find(Order.class, orderId);
    Member member = order.getMember(); // 참조 사용
    ```
- 위 내용이 내가 헷갈린 포인트와 맞닿아 있다.
- SQL 쿼리로 생각하면 JOIN을 하는게 당연한데 JPA에선 어떻게 조인을 하는지 (객체 참조를 하는지) 사고 흐름 자체가 달랐던 것 같다.
- 어느 정도 깨달음을 얻었지만 기초가 부족하니 책 완독 후 개발 진행할 예정.

#### 반복되는 CreatedAt, UpdatedAt 로직 Auditing으로 해결

> 23.07.11.

- Data JPA에서 제공하는 Auditing 기능을 적용
- BaseEntity에 @CreatedDate @LastModifiedDate 을 지정하고 Entity에 extends 해줌으로 반복되는 로직 해결

#### Post-Redirect-Get 패턴

> 23.07.11.

- Post로 글 작성 후 view만 바꿀 경우 Post가 다시 요청 될 수 있다.
- 이를 방지하기 위해 redirect를 통해 상세 페이지를 Get하면 Post 재요청을 방지할 수 있다.

### 배운 점

#### Test에서 Transactional

> 23.07.11.

- Test코드에서 @Transactional Annotation을 넣으면 Test용 동작이 실제 DB에 저장되지 않고 Rollback 된다.

#### @RequestParam -> ReqeustDTO로 받을 수 있음

> 23.07.11.

- RequestParam 때문에 코드 가독성이 떨어진다고 생각하던 찰나 DTO로 받을 수 있다는걸 알게 되었다.
- DTO 객체이기에 null값이 들어 올 수 있게 되었으므로 Error 처리도 더 쉬워질 것 같다.
