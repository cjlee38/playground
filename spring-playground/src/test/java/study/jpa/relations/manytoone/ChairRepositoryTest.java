package study.jpa.relations.manytoone;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
class ChairRepositoryTest {

    @Autowired
    private ChairRepository chairRepository;
    /**
     * ManyToOne 단방향에서, Many쪽의 fetch join 시 One쪽의 동일성이 보장되는지 확인한다.
     */
    @Test
    @DisplayName("ManyToOne 단방향에서, Many쪽의 fetch join 시 One쪽의 동일성이 보장되는지 확인한다.")
    void asdac(@Autowired EntityManager entityManager) {
        // given
        Desk desk = new Desk(null, 1);
        entityManager.persist(desk);

        Chair chairA = new Chair(null, "의자1", desk);
        Chair chairB = new Chair(null, "의자2", desk);
        Chair chairC = new Chair(null, "의자3", desk);
        chairRepository.saveAll(List.of(chairA, chairB, chairC));
        // when
        entityManager.flush();
        entityManager.clear();
        System.out.println("===========================");
        // then
        List<Chair> chairs = chairRepository.findAllWithDesks();
        assertThat(chairs.get(0).getDesk()).isSameAs(chairs.get(1).getDesk());

        // ensure
        Query query = entityManager.createQuery("select c from Chair c JOIN FETCH c.desk", Chair.class);
        List<Chair> resultList = query.getResultList();
        System.out.println("resultList.get(0).getDesk() = " + resultList.get(0).getDesk());
        System.out.println("resultList.get(1).getDesk() = " + resultList.get(1).getDesk());
    }
}
