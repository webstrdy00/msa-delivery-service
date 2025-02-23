package com.example.stock.paymentservice.messaging.publisher.kafka;

import com.example.stock.common.infrastructure.model.PaymentResponseAvroModel;
import com.example.stock.paymentservice.application.dto.PaymentResponse;
import com.example.stock.paymentservice.application.ports.output.PaymentResponseMessagePublisher;
import com.example.stock.paymentservice.messaging.mapper.PaymentMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.stock.common.infrastructure.kafka.KafkaConst.PAYMENT_TOPIC;

@RequiredArgsConstructor
@Component
public class PaymentResponseKafkaPublisher implements PaymentResponseMessagePublisher { // 결제 처리 결과를 Kafka를 통해 발행하는 퍼블리셔
    private final KafkaTemplate<String, PaymentResponseAvroModel> kafkaTemplate;
    private final PaymentMessagingMapper mapper;
    
    // 결제 처리 결과를 Kafka 토픽으로 발행
    @Override
    public void send(PaymentResponse paymentResponse) {
        // 결제 응답을 Avro 모델로 변환하여 PAYMENT_TOPIC으로 전송
        kafkaTemplate.send(
                PAYMENT_TOPIC,
                mapper.paymentResponseToAvroModel(paymentResponse)
        );
    }
}
