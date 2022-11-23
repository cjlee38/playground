package study.spring.transactional.propagation;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OuterServiceTest {

    @Autowired
    private OuterService outerService;

    @Autowired
    private MouseRepository mouseRepository;

    @Test
    void asdaxcz() {
        /**
         * 올바른 동작을 확인하기 위해선
         * hibernate.provider_disables_autocommit 옵션과 hikari.auto-commit 옵션을 꺼야 한다.
         */
        // given
        outerService.hello();

        // when
        List<Mouse> all = mouseRepository.findAll();
        System.out.println("all.size() = " + all.size());
        // then
    }
}
