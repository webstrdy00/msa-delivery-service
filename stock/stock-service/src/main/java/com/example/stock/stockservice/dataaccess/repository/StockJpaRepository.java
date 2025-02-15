package com.example.stock.stockservice.dataaccess.repository;

import com.example.stock.stockservice.dataaccess.entity.StockEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {   // JPA repository
    // 재고 수량을 감소시키는 쿼리 메서드
    // 동시성을 고려하여 재고가 충분한 경우에만 수량을 감소
    @Modifying
    @Query("UPDATE StockEntity s " +
            "SET s.availableQuantity = :quantity " +    // 감소량이 아닌 최종 수량으로 설정
            "WHERE s.productId = :productId")
    int decreaseQuantity(@Param("productId") UUID productId, @Param("quantity") int quantity);
}
