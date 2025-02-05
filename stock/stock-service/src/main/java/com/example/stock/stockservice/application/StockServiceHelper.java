package com.example.stock.stockservice.application;

import com.example.stock.common.aop.DistributedLock;
import com.example.stock.common.command.StockBuyCommand;
import com.example.stock.stockservice.application.ports.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StockServiceHelper {   // 재고 서비스의 핵심 비즈니스 로직을 처리하는 헬퍼 클래스
    private final OrderCommandHandler orderCommandHandler;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final StockDataMapper mapper;

    // 재고 정보 저장
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    // 상품 구매 처리 로직
    @Transactional
    public Order buy(StockBuyCommand stockBuyCommand) {
        checkQuantityAndDecrease(stockBuyCommand);

        // 주문 정보 저장 및 반환
        Order pandingOrder = orderRepository.save(
                mapper.stockBuyCommandToOrder(stockBuyCommand)
        );

        // TODO : kafka messaging -> payment service

        return pandingOrder;
    }

    // 분산 락이 적용된 재고 확인 및 감소 로직
    @DistributedLock
    private void checkQuantityAndDecrease(StockBuyCommand stockBuyCommand){
        // 상품 ID로 재고 정보 조회, 없으면 예외 발생
        Stock stock = stockRepository.findById(stockBuyCommand.productId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        // 구매 가능 수량 체크, 불가능하면 예외 발생
        if (!stock.isAvailableToBuy(stockBuyCommand.quantity())) {
            throw new RuntimeException("Not available to buy");
        }

        // 재고 확인 및 감소 처리
        boolean updated = stockRepository.decreaseQuantity(
                stockBuyCommand.productId(),
                stock.getAvailableQuantity() - stockBuyCommand.quantity()
        );

        if (!updated) {
            throw new RuntimeException("Out of Stock");
        }
    }
}
