package com.example.concurrencyplayground.app.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DomainEntityService {

    private final DomainEntityRepository domainEntityRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DomainEntity purchase(long id, long amount) {
        DomainEntity entity = domainEntityRepository.findById(id).orElseThrow();
        entity.purchase(amount);
        return entity;
    }
}
