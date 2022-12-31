package com.example.concurrencyplayground.facade;

import com.example.concurrencyplayground.app.entity.DomainEntity;
import com.example.concurrencyplayground.app.entity.DomainEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SynchronizedFacade {

    private final DomainEntityService service;

    public synchronized DomainEntity purchase(Long id, Long quantity) {
        return service.purchase(id, quantity);
    }
}
