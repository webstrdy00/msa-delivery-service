package com.example.stock.stockservice.messaging.publusher.kafka;

import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.stockservice.application.ports.output.OrderRequestMessagePublisher;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import com.example.stock.stockservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.stock.common.infrastructure.kafka.KafkaConst.ORDER_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderRequestMessageKafkaPublisher implements OrderRequestMessagePublisher {    // Kafka를 통해 주문 요청 메시지를 발행하는 퍼블리셔

    private final KafkaTemplate<String, OrderAvroModel> kafkaTemplate;
    private final OrderMessagingMapper mapper;

    // 주문 구매 이벤트를 Kafka 토픽으로 발행
    // 1. 이벤트를 Avro 모델로 변환
    // 2. 지정된 토픽으로 메시지 전송
    @Override
    public void publish(StockBuyEvent stockBuyEvent) {
        kafkaTemplate.send(ORDER_TOPIC,
                mapper.stockBuyEventToOrderAvroModel(stockBuyEvent));
    }
}
