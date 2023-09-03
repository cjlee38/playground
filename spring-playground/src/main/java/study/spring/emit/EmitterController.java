package study.spring.emit;

import java.util.concurrent.Executors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
public class EmitterController {
    @GetMapping("/emitter")
    public ResponseBodyEmitter /*See also : SseEmitter, StreamResponseBody ... */ emitter() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                for (int i = 1; i <= 50; i++) {
                    emitter.send("<p> Stream " + i + "</p>");
                    Thread.sleep(100L);
                }
            } catch (Exception ignored) {}
        });
        return emitter;
    }
}
