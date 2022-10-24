package study.spring.jpa.relations.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OneToOneUnidirectionalTest {

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Member {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private Account account;
    }

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String email;
    }

    @Test
    @DisplayName("OneToOne 단방향에서 Lazy Loading이 동작하는지 확인한다.")
    void aaa(@Autowired EntityManager entityManager) {
        Member member = new Member(null, "이름", new Account(null, "이메일"));

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        System.out.println("=============================================================");

        Member foundMember = entityManager.find(Member.class, member.id);
        System.out.println("foundMember = " + foundMember); // 쿼리 발생
        Account account = foundMember.getAccount();
        System.out.println("account = " + account); // 쿼리 발생
    }
}
