package study.spring.transactional.separation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
//@EnableJpaRepositories
class CoffeeServiceTest {

    @Autowired
    private CoffeeService coffeeService;

    @Test
    @DisplayName("커넥션을 지연해서 얻어오는지 확인한다.")
    void zxzxczxc() {
        // given\
        coffeeService.makeCoffee(987);
        // when

        // then
    }
}
