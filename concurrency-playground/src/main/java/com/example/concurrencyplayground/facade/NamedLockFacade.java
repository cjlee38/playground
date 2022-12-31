package com.example.concurrencyplayground.facade;

import com.example.concurrencyplayground.app.entity.DomainEntity;
import com.example.concurrencyplayground.app.entity.DomainEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NamedLockFacade {

    private final DomainEntityService service;
    private final LockRepository lockRepository;

    @Transactional
    public DomainEntity purchase(Long id, Long quantity) {
        try {
            lockRepository.getLock(id.toString());
            return service.purchase(id, quantity);
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
