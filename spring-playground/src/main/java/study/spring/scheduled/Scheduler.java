package study.spring.scheduled;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(fixedDelay = 1000L)
    public void test() {
        // breakpoint here !
        System.out.println("scheduled " + LocalDateTime.now());
    }
}
