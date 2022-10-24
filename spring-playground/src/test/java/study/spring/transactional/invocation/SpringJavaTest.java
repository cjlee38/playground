package study.spring.transactional.invocation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
@AutoConfigureTestDatabase
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
    @DisplayName("메소드를 호출했을 때 트랜잭션이 존재하는지 확인한다")
    void aa() {
        springJava.transactionalCaller();
        System.out.println("==========================================================");
        springJava.nonTransactionalCaller();
    }
}
