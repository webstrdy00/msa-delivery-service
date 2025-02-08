package com.example.stock.stockservice.messaging.mapper;

import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.common.infrastructure.model.PaymentResponseAvroModel;
import com.example.stock.stockservice.application.dto.PaymentResponse;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagingMapper { // 메시징을 위한 도메인 이벤트와 Avro 모델 간의 변환을 담당하는 매퍼

    // stockBuyEvent를 Avro 직렬화 가능한 모델로 변환
    // - UUID, userId, quantity 정보를 포함하여 변환
    // - Avro 스키마에 맞게 데이터 구조화
    public OrderAvroModel stockBuyEventToOrderAvroModel(StockBuyEvent stockBuyEvent) {
        return OrderAvroModel.newBuilder()
                .setProductId(stockBuyEvent.getProductId())
                .setUserId(stockBuyEvent.getUserId())
                .setQuantity(stockBuyEvent.getQuantity())
                .build();
    }

    // paymentResponse를 Avro 직렬화 가능한 모델로 변환
    public PaymentResponse paymentResponseAvroModelToPayResponse(PaymentResponseAvroModel paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .productId(paymentResponseAvroModel.getProductId())
                .orderId(paymentResponseAvroModel.getOrderId())
                .userId(paymentResponseAvroModel.getUserId())
                .paymentId(paymentResponseAvroModel.getPaymentId())
                .quantity(paymentResponseAvroModel.getQuantity())
                .build();
    }
}
