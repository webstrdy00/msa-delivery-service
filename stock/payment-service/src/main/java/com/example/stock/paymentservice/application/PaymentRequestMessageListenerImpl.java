package com.example.stock.paymentservice.application;

import com.example.stock.paymentservice.application.dto.PaymentRequest;
import com.example.stock.paymentservice.application.dto.PaymentResponse;
import com.example.stock.paymentservice.application.ports.input.message.PaymentRequestMessageListener;
import com.example.stock.paymentservice.application.ports.output.PaymentResponseMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {   // 결제 요청 메시지를 처리하는 리스너 구현체
    private final PaymentResponseMessagePublisher publisher;

    @Override
    public void completeOrder(PaymentRequest paymentRequest) {
        // PG Request...
        // Save Payment Result to Database...
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        // 결제 결과를 생성하여 발행
        publisher.send(new PaymentResponse(
                paymentRequest.getProductId(),
                paymentRequest.getOrderId(),
                paymentRequest.getUserId(),
                new SecureRandom().nextLong(),
                paymentRequest.getQuantity(),
                true
        ));
    }
}
