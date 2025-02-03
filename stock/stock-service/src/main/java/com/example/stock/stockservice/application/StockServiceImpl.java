package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.input.StockService;
import com.example.stock.stockservice.application.ports.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService {  // 재고 관련 비즈니스 로직을 처리하는 서비스 클래스

    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final StockDataMapper mapper;

    // 새로운 재고 정보를 저장하는 메서드
    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    // 상품 구매 처리 메서드
    @Override
    public Order buy(StockBuyEvent stockBuyEvent) {
        // 상품 ID로 재고 정보 조회, 없으면 예외 발생
        Stock stock = stockRepository.findById(stockBuyEvent.getProductId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        // 구매 가능 수량 체크, 불가능하면 예외 발생
        if (!stock.isAvailableToBuy(stockBuyEvent.getQuantity())) {
            throw new RuntimeException("Not available to buy");
        }


        // TODO : Move To Helper -> and convert domain to response
        // 재고 확인 및 감소 처리
        boolean updated = stockRepository.decreaseQuantity(
                stockBuyEvent.getProductId(),
                stockBuyEvent.getQuantity()
        );

        if (!updated) {
            // TODO : Out of stock response
        }

        // 주문 정보 저장 및 반환
        return orderRepository.save(
                mapper.stockBuyEventToOrder(stockBuyEvent)
        );
    }
}
