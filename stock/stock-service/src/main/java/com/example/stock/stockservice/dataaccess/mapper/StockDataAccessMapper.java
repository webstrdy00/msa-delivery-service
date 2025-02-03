package com.example.stock.stockservice.dataaccess.mapper;

import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.dataaccess.entity.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockDataAccessMapper {   // 엔티티와 도메인 객체 간의 변환을 담당하는 매퍼 클래스

    // StockEntity를 Stock 도메인 모델로 변환하는 메서드
    public Stock stockEntityToStock(StockEntity stockEntity) {
        return Stock.builder()
                .productId(stockEntity.getProductId())
                .brandId(stockEntity.getBrandId())
                .price(stockEntity.getPrice())
                .totalQuantity(stockEntity.getTotalQuantity())
                .availableQuantity(stockEntity.getAvailableQuantity())
                .orderStatus(stockEntity.getOrderStatus())
                .build();
    }

    // Stock 도메인 모델을 StockEntity로 변환하는 메서드
    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .productId(stock.getProductId())
                .brandId(stock.getBrandId())
                .price(stock.getPrice())
                .totalQuantity(stock.getTotalQuantity())
                .availableQuantity(stock.getAvailableQuantity())
                .orderStatus(stock.getOrderStatus())
                .build();
    }

}
