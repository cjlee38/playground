package study.spring.async;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
@RestController
public class NioController {

    @SneakyThrows
    @GetMapping("/normal")
    public ResponseEntity<String> normal() {
        log.info("normal");
        Thread.sleep(1000L);
        return ResponseEntity.ok("normal");
    }

    /**
     * this works in `MvcAsync` Thread <br>
     * condition) 100 requests, 10 servlet thread <br>
     * 1. Traditional Tomcat <br>
     *   -> 0~9 requests are being processed, 90 requests are waiting<br>
     *   -> 10~19 requests are being processed, 80 requests are waiting<br>
     * 2. Nio <br>
     * -> When return type specified as `Callable`, then new Thread(MvcAsync) created<br>
     * and servlet thread for current request(A) would go to process another request(B).<br>
     * When the job for A request is done, then the servlet thread comes back and response to client.
     */
    @SneakyThrows
    @GetMapping("/async")
    public Callable<String> async() {
        log.info("async start");
        return () -> {
            log.info("async callable");
            Thread.sleep(1000L);
            return "hello";
        };
    }
}
