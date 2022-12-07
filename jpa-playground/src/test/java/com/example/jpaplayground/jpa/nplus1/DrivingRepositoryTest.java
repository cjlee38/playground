package com.example.jpaplayground.jpa.nplus1;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DrivingRepositoryTest {

    @Autowired
    private DrivingRepository repository;

    @Autowired
    private EntityManager entityManager;

    /**
     * 100 개의 부모 테이블이 있고, batch size를 70으로 설정한다면, 다음과 같이 캐싱할 것
     * 70, 35, 17, 10, 9, 8, 7 ... 1
     * 결과는 다음과 같다.
     * 70, 17, 10, 3
     */
    @Test
    @DisplayName("batch fetch mode 설정에 따른 in 절의 개수를 확인한다.")
    void asdax() {
        // given
        for (int i = 1; i <= 100; i++) {
            List<BatchFetchMode> modes = new ArrayList<>();
            Driving driving = new Driving((long) i, modes);
            for (int j = 0; j < 10; j++) {
                modes.add(new BatchFetchMode(null, i + "~" + j));
            }
            repository.save(driving);
        }
        // when
        entityManager.flush();
        entityManager.clear();
        System.out.println("===============================================");
        // then
        List<Driving> drivings = repository.findAll();
        for (Driving driving : drivings) {
            int size = driving.getModes().size();
            System.out.println("size = " + size);
        }
    }
}
