package com.example.concurrencyplayground.facade;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.concurrencyplayground.app.entity.DomainEntity;
import com.example.concurrencyplayground.app.entity.DomainEntityRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SynchronizedFacadeTest {

    @Autowired
    private DomainEntityRepository repository;

    @Autowired
    private SynchronizedFacade facade;

    @Test
    void concurrentPurchase() throws InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(100);

        long startTime = System.nanoTime();
        // when
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    facade.purchase(1L, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        long elapsed = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        System.out.println("elapsed = " + elapsed); // 200ms
        DomainEntity domainEntity = repository.findById(1L).orElseThrow();
        System.out.println("domainEntity = " + domainEntity);
        assertThat(domainEntity.getQuantity()).isEqualTo(0L);
    }
}
