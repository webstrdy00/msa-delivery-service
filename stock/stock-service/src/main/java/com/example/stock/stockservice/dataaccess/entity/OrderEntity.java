package com.example.stock.stockservice.dataaccess.entity;

import com.example.stock.stockservice.core.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {  // 주문 정보 엔티티 클래스
    @Id
    private UUID id;

    private Long userId;

    private UUID productId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Version
    private Long version;   // JPA 낙관적 락을 위한 버전 필드

    // equals와 hashCode는 id 필드만 사용하여 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
