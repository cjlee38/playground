package study.spring.transactional.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

    @SneakyThrows
    @Async
    @Transactional
    public void helloWorld() {
        Thread.sleep(500);
        String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        boolean active = TransactionSynchronizationManager.isActualTransactionActive();
        logger.info("==============hello world in async ==== " + currentTransactionName + "active = " + active);
    }

    @Transactional
    public String text() {
        // self-invocation이기 떄문에 아무런 의미가 없다.
        for (int i = 0; i < 1000; i++) {
            helloWorld(); // useless
        }
        return "text!";
    }
}
