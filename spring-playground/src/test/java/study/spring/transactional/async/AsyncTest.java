package study.spring.transactional.async;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncTest {

    @Autowired
    private AsyncCaller asyncCaller;

    @Test
    @DisplayName("제대로 잘 안된다. 어플리케이션을 직접 띄우니까 잘됨")
    void asdasd() {
        // given
        asyncCaller.callAsync();
        // when

        // then
    }
}
