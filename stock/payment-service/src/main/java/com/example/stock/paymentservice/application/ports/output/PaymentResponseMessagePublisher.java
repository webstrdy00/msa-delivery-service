package com.example.stock.paymentservice.application.ports.output;

import com.example.stock.paymentservice.application.dto.PaymentResponse;

public interface PaymentResponseMessagePublisher {
    void send(PaymentResponse paymentResponse);
}
