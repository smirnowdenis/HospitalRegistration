package com.company.hospital.kafka;

import com.company.hospital.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class Listener {

    private final RegistrationService registrationService;

    @KafkaListener(topics = "${kafka.registration-topic}",
            groupId = "${kafka.group-id}")
    public void createRegistration(String message) {
        log.info("Received registration message");
        registrationService.createRegistration(message);
        log.info("Finished registration message");
    }
}
