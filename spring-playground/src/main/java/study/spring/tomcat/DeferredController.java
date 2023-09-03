package study.spring.tomcat;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Slf4j
public class DeferredController {

    private Queue<DeferredResult<String>> queue = new ConcurrentLinkedQueue<>();

    /**
     * This endpoint hangs the response, until the `DeferredResult` object get result or timeout
     */
    @GetMapping("/deferred")
    public DeferredResult<String> defer() {
        log.info("defer");
        DeferredResult<String> dr = new DeferredResult<>(/* timeout */);
        queue.add(dr);
        return dr;
    }

    @GetMapping("/deferred/count")
    public String count() {
        log.info("defer count");
        return String.valueOf(queue.size());
    }

    /**
     * When `DeferredResult` getting result, the `deferred` would work
     */
    @GetMapping("/deferred/event")
    public String event(String message) {
        for (DeferredResult<String> dr : queue) {
            dr.setResult("event = " + message);
        }
        return "OK";
    }
}
