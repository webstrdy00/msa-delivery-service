package com.example.stock.paymentservice.messaging.mapper;

import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.common.infrastructure.model.PaymentResponseAvroModel;
import com.example.stock.paymentservice.application.dto.PaymentRequest;
import com.example.stock.paymentservice.application.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessagingMapper {

    // orderAvroModel를 PaymentRequest로 변환
    public PaymentRequest orderAvroModelToPaymentRequest(OrderAvroModel orderAvroModel) {
        return PaymentRequest.builder()
                .productId(orderAvroModel.getProductId())
                .orderId(orderAvroModel.getOrderId())
                .userId(orderAvroModel.getUserId())
                .quantity(orderAvroModel.getQuantity())
                .build();
    }

    // PaymentResponse를 PaymentResponseAvroMedel로 변환
    // Kafka를 통한 메시지 전송을 위한 직렬화 가능한 형태로 변환
    public PaymentResponseAvroModel paymentResponseToAvroModel(PaymentResponse paymentResponse) {
        return PaymentResponseAvroModel.newBuilder()
                .setProductId(paymentResponse.getProductId())
                .setOrderId(paymentResponse.getOrderId())
                .setUserId(paymentResponse.getUserId())
                .setPaymentId(paymentResponse.getPaymentId())
                .setQuantity(paymentResponse.getQuantity())
                .setResult(paymentResponse.isResult())
                .build();

    }
}
