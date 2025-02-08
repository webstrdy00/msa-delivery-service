package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.dto.PaymentResponse;
import com.example.stock.stockservice.application.ports.input.message.PaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {     // 결제 응답 메시지 리스너 구현체

    private final OrderPaymentSaga orderPaymentSaga;

    // 결제 완료 메시지 처리
    @Override
    public void paymentComplete(PaymentResponse paymentResponse) {
        orderPaymentSaga.process(paymentResponse);
    }

    // 결제 취소 메시지 처리
    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        orderPaymentSaga.rollback(paymentResponse);
    }
}
