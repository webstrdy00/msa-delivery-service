package com.example.stock.stockservice.dataaccess.entity;

import com.example.stock.stockservice.core.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockEntity {   // 엔티티 클래스
    @Id
    private UUID productId;

    private String brandId;

    private int price;

    private int totalQuantity;

    private int availableQuantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Version
    private Long version;    // JPA 낙관적 락을 위한 버전 필드

    // 엔티티 식별을 위한 equals와 hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
