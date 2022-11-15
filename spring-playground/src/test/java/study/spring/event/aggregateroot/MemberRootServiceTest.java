package study.spring.event.aggregateroot;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRootServiceTest {

    @Autowired
    private MemberRootService memberRootService;

    @Test
    @DisplayName("aggregate root에서 이벤트를 발행하는 타이밍을 확인한다.")
    void testNormal() {
        memberRootService.loginNormalMember("hello normal");
        /**
         * save를 하지 않으면 일반 이벤트가 올바르게 발행되지 않는다. 즉, 아무런 리스너도 호출하지 않는다.
         * save를 하면, 일반 이벤트가 발행된다.
         * 트랜잭셔널 이벤트 또한, save 메소드에 붙어있는 `@transactional` 에 의해 동작한다.
         */
    }

    @Test
    @DisplayName("aggregate root에서 트랜잭셔널 이벤트를 발행하는 타이밍을 확인한다.")
    void testTransactional() {
        memberRootService.loginTransactionalMember("hello transactional");
        /**
         * save 메소드를 호출하는 순간 일반 이벤트를 발행한다.
         * 트랜잭션이 commit 되고, 마무리하려는 시점에 트랜잭셔널이벤트를 발행한다.
         */
    }
}
