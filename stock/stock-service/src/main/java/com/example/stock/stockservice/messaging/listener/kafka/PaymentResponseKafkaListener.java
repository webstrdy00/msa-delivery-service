package com.example.stock.stockservice.messaging.listener.kafka;

import com.example.stock.common.infrastructure.model.PaymentResponseAvroModel;
import com.example.stock.stockservice.application.ports.input.message.PaymentResponseMessageListener;
import com.example.stock.stockservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.stock.common.infrastructure.kafka.KafkaConst.PAYMENT_TOPIC;

@RequiredArgsConstructor
@Component
public class PaymentResponseKafkaListener {     // Kafka 결제 응답 메시지 리스너

    private final PaymentResponseMessageListener paymentResponseMessageListener;
    private final OrderMessagingMapper mapper;

    // 결제 응답 토픽 구독
    @KafkaListener(topics = PAYMENT_TOPIC, groupId = "spring")
    public void consumer(@Payload PaymentResponseAvroModel paymentResponseAvroModel){
        // 결제 결과에 따른 처리
        if (paymentResponseAvroModel.getResult()){
            paymentResponseMessageListener.paymentComplete(
                    mapper.paymentResponseAvroModelToPayResponse(paymentResponseAvroModel)
            );
        }else{
            paymentResponseMessageListener.paymentCancelled(
                    mapper.paymentResponseAvroModelToPayResponse(paymentResponseAvroModel)
            );
        }
    }
}
