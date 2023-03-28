package study.spring.transactional.readonly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReadOnlyServiceTest {

    @Autowired
    private NotReadOnlyService notReadOnlyService;

    @Autowired
    private ReadOnlyService readOnlyService;

    @Test
    void test() {
        readOnlyService.test();
    }
}
