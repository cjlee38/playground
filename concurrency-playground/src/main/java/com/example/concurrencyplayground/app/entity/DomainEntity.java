package com.example.concurrencyplayground.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Long quantity;

    public void purchase(long amount) {
        if (this.quantity - amount < 0) {
            throw new IllegalStateException(this.quantity + "개에서 " + amount + "개를 구매할 수 없습니다.");
        }
        this.quantity -= amount;
    }
}
