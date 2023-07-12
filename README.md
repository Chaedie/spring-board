### 트러블 슈팅

#### 게시글 작성 시 게시글 당 여러 파일 저장이 어려움 (JPA Mapping 개념 부족)

> 23.07.12. 20:09

- 이를 해결하기 위해 우선 한 게시글 당 하나의 파일만 저장되도록 구현할 예정
- 이후 SPRING 강의와 JPA 학습 후 다시 작성해볼 예정

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
