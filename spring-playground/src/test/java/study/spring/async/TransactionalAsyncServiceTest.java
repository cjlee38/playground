package study.spring.async;

import groovy.util.logging.Slf4j;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Slf4j
class TransactionalAsyncServiceTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final AsyncService asyncService;

    public TransactionalAsyncServiceTest(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @Test
    void testPrimitive() {
        logger.info("run()");
        String result = asyncService.helloPrimitive();
        logger.info("result = " + result); // Note. (kotlin) this returns null even if declared as not-null !
        logger.info("exit");
    }

    @SneakyThrows
    @Test
    void testAsync() {
        logger.info("run()");
        Future<String> result = asyncService.helloAsync();
        logger.info("isDone = " + result.isDone());
        logger.info("result = " + result.get());
        logger.info("exit");
    }

    @SneakyThrows
    @Test
    void testCallBack() {
        logger.info("run()");
        ListenableFuture<String> result = asyncService.helloListenable();
        result.addCallback(
                System.out::println,
                (e) -> System.out.println(e.getMessage())
        );
        // result.cancel(true) // might be cancelled
        logger.info("exit");

        Thread.sleep(2000L);
    }
}
