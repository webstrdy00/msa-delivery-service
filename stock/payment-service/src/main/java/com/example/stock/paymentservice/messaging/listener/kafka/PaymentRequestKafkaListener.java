package com.example.stock.paymentservice.messaging.listener.kafka;

import com.example.stock.common.infrastructure.kafka.KafkaConst;
import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.paymentservice.application.ports.input.message.PaymentRequestMessageListener;
import com.example.stock.paymentservice.messaging.mapper.PaymentMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentRequestKafkaListener {  // Kafka를 통해 들어오는 결제 요청 메시지를 처리하는 리스너
    private final PaymentRequestMessageListener paymentRequestMessageListener;
    private final PaymentMessagingMapper mapper;

    // ORDER_TOPIC으로부터 주문 메시지를 구독하여 처리
    @KafkaListener(topics = KafkaConst.ORDER_TOPIC, groupId = "spring")
    public void consumer(@Payload OrderAvroModel orderAvroModel) {
        // Avro 모델을 PaymentRequest로 변호나해서 처리
        paymentRequestMessageListener.completeOrder(
                mapper.orderAvroModelToPaymentRequest(orderAvroModel)
        );
    }
}
