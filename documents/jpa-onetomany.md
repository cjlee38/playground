
# JPA 에서 OneToMany 단방향을 사용하면 어떻게 될까 ?

[테스트 코드](../spring-playground/src/test/java/study/spring/jpa/relations/RelationQueryTest.java)

## 양방향을 사용하는 경우

```java

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<Member> members;
}


public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name) {
        this.id = null;
        this.name = name;
    }
}
```

이러한 상태라면, 평범한 쿼리가 나간다.

```java
/*
select
    team0_.id as id1_1_0_,
    team0_.name as name2_1_0_
from
    team team0_
where
    team0_.id=?
*/
Team findTeam = teamRepository.findById(team.getId()).orElseThrow();

/*
select
    members0_.team_id as team_id3_0_0_,
    members0_.id as id1_0_0_,
    members0_.id as id1_0_1_,
    members0_.name as name2_0_1_,
    members0_.team_id as team_id3_0_1_
from
    member members0_
where
    members0_.team_id=?
 */
for (Member member : findTeam.getMembers()) {
    System.out.println("member = " + member);
}
```

## 단방향을 사용하는 경우
- 중간 테이블 (TeamMembers 를 자동으로 생성한다.)
- 해당 중간 테이블에서 team_id와 member_id를 들고 있는다.
- team에서 member에 대한 조회가 필요한 경우 team_member 테이블로부터 member id 목록을 가져와 쿼리를 날린다.


```sql
select
    members0_.team_id as team_id1_2_0_,
    members0_.members_id as members_2_2_0_,
    member1_.id as id1_0_1_,
    member1_.name as name2_0_1_
from
    team_members members0_
inner join
    member member1_
    on members0_.members_id = member1_.id
where
    members0_.team_id =?
```

## 단방향 + Set 인 경우
- 쿼리 상에서는 차이가 없는 것 같다.
