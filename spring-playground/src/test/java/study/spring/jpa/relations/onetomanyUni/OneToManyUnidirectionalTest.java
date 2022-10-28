package study.spring.jpa.relations.onetomanyUni;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/*
OneToMany 단방향 연관관계일 때, 옵션에 따라 어떻게 동작하는지 확인한다.
조합 후보
nullable = true/false
updatable = true/false
orphanRemoval = true/false
 */
@DataJpaTest
public class OneToManyUnidirectionalTest {

    @Entity
    static class Team {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "team_id", nullable = false)
        private List<Member> members = new ArrayList<>();

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<Member> getMembers() {
            return members;
        }
    }

    @Entity
    static class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
    }

    @Test
    void asd(@Autowired EntityManager entityManager) {
        // given
        Team team = new Team();
        team.name = "hello";
        // when
        Member member = new Member();
        member.name = "membr";

        team.getMembers().add(member);
        // then
        entityManager.persist(team);
        entityManager.flush();
        entityManager.clear();
        System.out.println("==========================");
    }

    @Test
    void xxasd(@Autowired EntityManager entityManager) {
        // given
        Team team = new Team();
        team.name = "hello";

        Team team2 = new Team();
        team2.name = "hello";
        // when
        Member member = new Member();
        member.name = "membr";

        team.getMembers().add(member);
        // then
        entityManager.persist(team);
        entityManager.persist(team2);
        entityManager.flush();
        entityManager.clear();
        System.out.println("==========================");

        Team team1 = entityManager.find(Team.class, team.getId());
//        Member findMember = entityManager.find(Member.class, member.id);
        Member findMember = team1.getMembers().remove(0);
//        Member findMember = team1.getMembers().get(0);
        Team team22 = entityManager.find(Team.class, team2.getId());
        //team22.getMembers().add(findMember);

//        entityManager.flush();
//        team22.getMembers().add(findMember);

        entityManager.flush();
        entityManager.clear();

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
