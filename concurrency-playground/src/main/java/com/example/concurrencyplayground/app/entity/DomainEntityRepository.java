package com.example.concurrencyplayground.app.entity;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainEntityRepository extends JpaRepository<DomainEntity, Long> {
}
