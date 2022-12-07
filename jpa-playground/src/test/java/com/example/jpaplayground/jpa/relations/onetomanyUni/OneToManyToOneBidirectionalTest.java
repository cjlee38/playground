package com.example.jpaplayground.jpa.relations.onetomanyUni;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OneToManyToOneBidirectionalTest {

    @Entity
    static class Team {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        // cascade를 all(혹은 remove)로 준다면
        // team 삭제시 member도 삭제된다.
        // 반대로 persist만 남기게 된다면
        // team 삭제시 member는 삭제되지 않는다.
        @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
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

        @ManyToOne
        @JoinColumn(name = "member_id")
        private Team team;
    }

    @Autowired
    private EntityManager entityManager;

    @Test
    void asd() {
        // given
        Team team = new Team();
        team.name = "myTeam";
        Member member = new Member();
        member.name = "myMember";
        team.members.add(member);
        member.team = team;

        entityManager.persist(team);
        entityManager.flush();
        entityManager.clear();

        // when

        Team findTeam = entityManager.find(Team.class, team.getId());
        entityManager.remove(findTeam);

        // then
        entityManager.flush();
        entityManager.clear();

    }
}
