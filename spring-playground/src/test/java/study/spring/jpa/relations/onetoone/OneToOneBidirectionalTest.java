package study.spring.jpa.relations.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OneToOneBidirectionalTest {

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
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
    @Getter @Setter
    static class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String email;

        @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
        private Member member;
    }

    @Test
    @DisplayName("OneToOne 양방향에서 Lazy Loading이 동작하는지 확인한다.(member -> account)")
    void aaa(@Autowired EntityManager entityManager) {
        Member member = new Member(null, "이름", null);
        Account account = new Account(null, "이메일", member);
        member.setAccount(account);

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        System.out.println("=============================================================");

        Member foundMember = entityManager.find(Member.class, member.id);
        System.out.println("foundMember = " + foundMember); // 쿼리 발생
        Account foundAccount = foundMember.getAccount();
        System.out.println("account = " + foundAccount); // 쿼리 발생
    }

    @Test
    @DisplayName("OneToOne 양방향에서 Lazy Loading이 동작하는지 확인한다.(account -> member)")
    void aa2a(@Autowired EntityManager entityManager) {
        Member member = new Member(null, "이름", null);
        Account account = new Account(null, "이메일", member);
        member.setAccount(account);

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        System.out.println("=============================================================");

        Account foundAccount = entityManager.find(Account.class, account.getId()); // 여기서는 select 쿼리가 두번 나간다.
    }
}
