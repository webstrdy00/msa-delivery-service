package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.input.web.OrderStatusCommand;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.application.ports.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderCommandHandler {  // 주문 관련 명령을 처리하는 핸들러 클래스
    private final StockDataMapper mapper;
    private final OrderRepository orderRepository;

    // 주문 상태 조회 처리 메서드
    public OrderStatusResponse getOrderStatus(OrderStatusCommand orderStatusCommand) {
        return orderRepository.findById(orderStatusCommand.orderId())
                .map(mapper::orderToOrderStatusResponse)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
    }
}
