package com.example.concurrencyplayground.app.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PurchaseRequest {
    private final Long id;
    private final Long amount;
}
