package study.spring.transactional.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AsyncCaller {

    private final AsyncService asyncService;

    @Transactional
    public String callAsync() {
        for (int i = 0; i < 1000; i++) {
            asyncService.helloWorld();
        }
        return "caller";
    }

    public String doOnPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(asyncService::helloWorld);
        }
        return "poollllllllll";
    }
}
