package com.example.stock.stockservice.application;

import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.application.dto.PaymentResponse;
import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.stock.stockservice.core.enums.OrderStatus.CANCELLED;
import static com.example.stock.stockservice.core.enums.OrderStatus.PAID;

@RequiredArgsConstructor
@Component
public class OrderPaymentSaga {     // 주문-결제 간 사가 패턴 구현 클래스

    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;

    // 결제 성공 처리
    @Transactional
    public void process(PaymentResponse paymentResponse) {
        // 주문 상태를 PAID로 변경
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
        orderRepository.save(order.updateStatus(PAID));

        // Outbox 메시지 상태를 COMPLETED로 변경
        OrderOutboxMessage orderOutboxMessage = orderOutboxRepository.findByOrderId(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Message Not Found"));
        orderOutboxMessage.updateStatus(OutboxStatus.COMPLETED);
        orderOutboxRepository.save(orderOutboxMessage);
    }

    // 결제 실패 보상 트랜잭션
    @Transactional
    public void rollback(PaymentResponse paymentResponse) {
        // 주문 상태를 CANCELLED로 변경
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
        orderRepository.save(order.updateStatus(CANCELLED));

        // Outbox 메시지 상태를 FAILED로 변경
        OrderOutboxMessage orderOutboxMessage = orderOutboxRepository.findByOrderId(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Message Not Found"));
        orderOutboxMessage.updateStatus(OutboxStatus.FAILED);
        orderOutboxRepository.save(orderOutboxMessage);
    }
}
