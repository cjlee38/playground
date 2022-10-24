package study.spring.jpa.columnattribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class Tester {

    @Autowired
    private EntityManager manager;

    @Entity
    static class Temp {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(updatable = false)
        private String name;

        public Temp(String name) {
            this.name = name;
        }

        public Temp() {
        }

        public String getName() {
            return name;
        }
    }

    @Test
    void asd() {
        Temp hello = new Temp("hello");
        // given
        // when
        manager.persist(hello);
        manager.flush();
        manager.clear();

        Temp findTemp = manager.find(Temp.class, 1L);
        System.out.println("findTemp.getClass() = " + findTemp.getClass());
        // then
        hello.name = "world";
        manager.flush();
    }
}
