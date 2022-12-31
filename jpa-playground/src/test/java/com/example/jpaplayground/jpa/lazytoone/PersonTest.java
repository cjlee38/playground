package com.example.jpaplayground.jpa.lazytoone;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class PersonTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @Test
    void test() {
        // given
        Phone phone = new Phone(null, "01012341234");
        Person person = new Person(null, "my-person", phone);
        em.persist(phone);
        em.persist(person);
        em.flush();
        em.clear();
        // when

        Person findPerson = em.find(Person.class, person.getId());
        System.out.println("=================================");
        // then
        System.out.println(findPerson);
    }
}
