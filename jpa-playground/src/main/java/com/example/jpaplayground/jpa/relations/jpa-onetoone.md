# OneToOne 사용시 주의사항.

[단방향 코드](../spring-playground/src/test/java/study/spring/jpa/relations/onetoone/OneToOneUnidirectionalTest.java)
[양방향 코드](../spring-playground/src/test/java/study/spring/jpa/relations/onetoone/OneToOneBidirectionalTest.java)

- OneToOne은 양방향인 경우, 외래키가 없는(연관관계의 주인이 아닌) 엔티티를 조회하는 경우 select 쿼리가 두번 나간다.

```java
class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Account account;
}

class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private Member member;
}

```

위와 같은 상황일 때, 연관관계의 주인은 `Member`이다. 즉, `member` 테이블에 `account_id` 가 존재한다.
여기서 `Member`를 조회한다면, `account_id` 를 알고 있기 때문에 프록시객체로 들고 있을 수 있다.

`Account`는 어떨까? `Account`를 조회해왔을 때 account 내부의 member 객체의 id를 알기 위해선 member 테이블을 조회해야 한다.

하지만 이는 OneToOne 이 아니라 OneToMany 일때도 마찬가지인데, 
Many는 비어있는 프록시 객체 (PersistentBag)로 들고있을 수 있지만, Member 는 일반 객체이므로 들고 있을 수 없다.

> 의문. Member를 조회했을 때는 Account를 프록시 객체로 들고 있을 수 있다. 그렇다면 Account를 조회했을 때 Member 객체를 프록시로 들고 있다가, 나중에 사용할 때
> `select * fro member where account_id=?` 로 조회해오면 안될까 ? 이게 불가능하다면, 그 이유는 "프록시 객체는 반드시 id까지 들고 있어야 한다" 라는 가설이 성립되어야 한다.
> 


[참고1](https://thorben-janssen.com/hibernate-tip-lazy-loading-one-to-one/)
[참고2](https://developer.jboss.org/docs/DOC-13960)
