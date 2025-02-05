package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.StockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class StockRepositoryImpl implements StockRepository {   // 도메인 모델 repository 구현체
    private static final int ONLY_ONE_AFFECTED_ROW = 1; // 상수로 업데이트 성공 기준 정의

    private final StockJpaRepository stockJpaRepository;
    private final StockDataAccessMapper mapper;

    // 상품 ID로 재고 정보를 조회하는 메서드
    @Override
    public Optional<Stock> findById(UUID productId) {
        return stockJpaRepository.findById(productId)
                .map(mapper::stockEntityToStock);
    }

    // 재고 정보를 저장하는 메서드
    @Override
    public Stock save(Stock stock) {
        return mapper.stockEntityToStock(
                stockJpaRepository.save(mapper.stockToStockEntity(stock))
        );
    }

    // 재고 수량을 감소시키는 매서드
    @Override
    // TODO : Optimistic Locking
    public boolean decreaseQuantity(UUID productId, int quantity) {
        return stockJpaRepository.decreaseQuantity(productId, quantity)
                == ONLY_ONE_AFFECTED_ROW;
    }
}
