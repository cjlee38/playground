package com.example.concurrencyplayground.app.entity;

import static com.example.concurrencyplayground.SimpleRestAssured.patch;
import static com.example.concurrencyplayground.SimpleRestAssured.toObject;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.concurrencyplayground.app.entity.DomainEntityController.PurchaseRequest;
import io.restassured.RestAssured;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DomainEntityIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    private DomainEntityRepository repository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void plainPurchase() {
        PurchaseRequest request = new PurchaseRequest(1L, 5L);
        DomainEntity result = toObject(patch("/domainentity", request), DomainEntity.class);
        System.out.println("result = " + result);
    }

    @Test
    void concurrentPurchase() throws InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    patch("/domainentity", new PurchaseRequest(1L, 1L));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        DomainEntity domainEntity = repository.findById(1L).orElseThrow();
        System.out.println("domainEntity = " + domainEntity);
        assertThat(domainEntity.getQuantity()).isEqualTo(0L);
    }
}
