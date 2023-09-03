package study.spring.async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;

@Service
@EnableAsync
@Slf4j
class AsyncService {

    @SneakyThrows
    @Async
    public String helloPrimitive() {
        log.info("hello primitive");
        Thread.sleep(2000L);
        return "hello !";
    }

    @SneakyThrows
    @Async
    public Future<String> helloAsync(){
        log.info("hello async");
        Thread.sleep(2000L);
        return new AsyncResult("hello !");
    }

    @SneakyThrows
    @Async
    public ListenableFuture<String> helloListenable() {
        log.info("hello listenable");
        Thread.sleep(2000L);
        return new AsyncResult("hello !");
    }
}
