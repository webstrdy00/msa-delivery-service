package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.output.OrderExternalMessageListener;
import com.example.stock.stockservice.application.ports.output.OrderRequestMessagePublisher;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderExternalMessageListenerImpl implements OrderExternalMessageListener { // 외부 메시지 발행을 처리하는 리스너

    private final OrderRequestMessagePublisher orderRequestMessagePublisher;

    // 트랜잭션 완료 후 비동기로 메시지 발행 처리
    // @Async: 비동기 실행을 위한 어노테이션
    // @TransactionalEventListener: 트랜잭션 완료 후 이벤트 처리
    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(StockBuyEvent stockBuyEvent) {
        orderRequestMessagePublisher.publish(stockBuyEvent);
    }
}
