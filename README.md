### 트러블 슈팅

#### 반복되는 CreatedAt, UpdatedAt 로직 Auditing으로 해결

- Data JPA에서 제공하는 Auditing 기능을 적용
- BaseEntity에 @CreatedDate @LastModifiedDate 을 지정하고 Entity에 extends 해줌으로 반복되는 로직 해결

#### Post-Redirect-Get 패턴

- Post로 글 작성 후 view만 바꿀 경우 Post가 다시 요청 될 수 있다.
- 이를 방지하기 위해 redirect를 통해 상세 페이지를 Get하면 Post 재요청을 방지할 수 있다.

### 배운 점

#### Test에서 Transactional

- Test코드에서 @Transactional Annotation을 넣으면 Test용 동작이 실제 DB에 저장되지 않고 Rollback 된다.