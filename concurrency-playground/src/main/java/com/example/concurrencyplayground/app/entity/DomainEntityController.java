package com.example.concurrencyplayground.app.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DomainEntityController {

    private final DomainEntityService domainEntityService;

    @PatchMapping("/domainentity")
    public ResponseEntity<DomainEntity> hello(@RequestBody PurchaseRequest request) {
        return ResponseEntity.ok(domainEntityService.purchase(request.getId(), request.getAmount()));
    }
}
