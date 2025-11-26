package com.Expenses.AuthService.KafkaEventProducer;

import com.Expenses.AuthService.Dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Service
@RequiredArgsConstructor
public class UserInfoEventProducer {

    @Value("${spring.kafka.template.default-topic}")
    private String TOPIC_NAME;

    private final KafkaTemplate<String, UserInfoDto> kafkaTemplate;

    public void sendEventToKafka(UserInfoDto userInfoDto){
        Message<UserInfoDto> message = MessageBuilder
                .withPayload(userInfoDto)
                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
                .build();

        kafkaTemplate.send(message);
    }
}
