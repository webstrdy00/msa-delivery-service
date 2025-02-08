package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.StockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
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

    // 낙관적 락을 사용한 재고 감소 처리
    @Override
    public boolean decreaseQuantity(UUID productId, int quantity) {
        Integer result = null;
        try {
            result = stockJpaRepository.decreaseQuantity(productId, quantity);
        } catch (OptimisticLockingFailureException e) {
            // 낙관적 락 충돌 발생 시 예외 처리
            throw new RuntimeException("Lost Update Occurred");
        }
        return result == ONLY_ONE_AFFECTED_ROW;
    }
}
