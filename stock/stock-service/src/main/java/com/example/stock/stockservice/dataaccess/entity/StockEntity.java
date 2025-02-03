package com.example.stock.stockservice.dataaccess.entity;

import com.example.stock.stockservice.core.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
