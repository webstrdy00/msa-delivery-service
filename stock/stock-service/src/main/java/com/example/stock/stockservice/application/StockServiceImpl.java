package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.input.StockService;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusCommand;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.application.ports.input.web.StockBuyCommand;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService {  // 재고 관련 비즈니스 로직을 처리하는 서비스 클래스

    private final StockServiceHelper stockServiceHelper;
    private final OrderCommandHandler orderCommandHandler;

    // 새로운 재고 정보를 저장하는 메서드
    @Override
    public Stock save(Stock stock) {
        return stockServiceHelper.save(stock);
    }

    // 상품 구매 처리 메서드
    @Override
    public Order buy(StockBuyCommand command) {
        // TODO : Kafka Messaging
        return stockServiceHelper.buy(
                new StockBuyEvent(
                        command.productId(),
                        command.quantity()
                )
        );
    }

    // 주문 상태 조회
    @Override
    public OrderStatusResponse getOrderStatus(OrderStatusCommand orderStatusCommand) {
        return orderCommandHandler.getOrderStatus(orderStatusCommand);
    }
}
