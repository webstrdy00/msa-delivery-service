package com.example.stock.stockservice.dataaccess.entity;

import com.example.stock.stockservice.core.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {  // 주문 정보 엔티티 클래스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private UUID productId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
