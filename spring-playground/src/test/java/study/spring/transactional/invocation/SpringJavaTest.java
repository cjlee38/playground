package study.spring.transactional.invocation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class SpringJavaTest {

    @TestConfiguration
    static class Config {

        @Bean
        public SpringJava getSpringJava() {
            return new SpringJava();
        }
    }

    @Autowired
    private SpringJava springJava;

    @Test
    void aa() {
        springJava.nonTransactionalCaller();
    }
}
