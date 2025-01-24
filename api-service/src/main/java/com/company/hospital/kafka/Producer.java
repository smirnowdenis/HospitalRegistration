package com.company.hospital.kafka;

import com.company.hospital.config.KafkaProperties;
import com.company.hospital.dto.RegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaProperties kafkaProperties;

    private final ObjectMapper objectMapper;

    public void sendMessage(RegistrationDto msg) {
        try {
            kafkaTemplate.send(kafkaProperties.getRegistrationTopic(), objectMapper.writeValueAsString(msg));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}