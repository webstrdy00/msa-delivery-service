package com.example.stock.stockservice.application.ports.input.web;

import com.example.stock.common.command.StockBuyCommand;
import com.example.stock.stockservice.application.ports.input.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/order")
public class OrderController {
    private final StockService stockService;

    // 상품 구매 요청
    @PostMapping("/buy")
    public String buy(@RequestBody StockBuyCommand command) {
        stockService.buy(command);

        return "OK";
    }

    // 주문 상태 조회
    @PostMapping("/status")
    public OrderStatusResponse getOrderStatus(@RequestBody OrderStatusCommand command) {
        return stockService.getOrderStatus(command);
    }
}
