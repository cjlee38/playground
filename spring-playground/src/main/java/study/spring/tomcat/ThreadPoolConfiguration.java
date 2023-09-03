package study.spring.tomcat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfiguration {

    /**
     * ORDER
     * 1. core 2. queue 3. max <br>
     * If 11 requests come at once, 11th request would be waited in queue, <br>
     * which means thread pool does not allocate new thread for these requests) <br>
     * Otherwise, If 211(10 + 200 + 1) requests come at once, new thread would be created <br>
     * because max pool size is bigger than current(which is 10 by core size).
     */
    @Bean
    public TaskExecutor pool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(200);
        return executor;
    }
}
