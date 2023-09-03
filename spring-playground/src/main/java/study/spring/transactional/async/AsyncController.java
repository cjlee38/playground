package study.spring.transactional.async;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncCaller caller;
    private final TransactionalAsyncService service;

    @GetMapping("/async/run")
    public void runAsync() {
        caller.callAsync();
    }

    @GetMapping("/async/text")
    @ResponseBody
    public ResponseEntity<String> getText() {
        return ResponseEntity.ok(caller.callAsync());
    }

    @GetMapping("/async/pool")
    @ResponseBody
    public ResponseEntity<String> doOnPool() {
        return ResponseEntity.ok(caller.doOnPool());
    }
}
