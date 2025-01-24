package com.company.hospital.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import com.company.hospital.config.ApplicationProperties;
import com.company.hospital.dto.RegistrationDto;
import com.company.hospital.kafka.Producer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RegistrationService {

    private final Producer producer;
    private final RestTemplate restTemplate;

    private static final String PROTOCOL = "http://";
    private static final String REGISTRATION_PATH = "/registration";

    private final URI baseUri;

    public RegistrationService(Producer producer, RestTemplate restTemplate,
                               ApplicationProperties applicationProperties) {
        this.producer = producer;
        this.restTemplate = restTemplate;
        this.baseUri = URI.create(PROTOCOL + applicationProperties.getUrl());
    }

    public void createRegistration(RegistrationDto registrationDto) {
        producer.sendMessage(registrationDto);
    }

    public List<RegistrationDto> getRegistrations() {
        var uri = baseUri.resolve(REGISTRATION_PATH + "/");
        var exchange = restTemplate.exchange(uri, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<RegistrationDto>>() {
                });
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return exchange.getBody();
        } else {
            throw new RestClientException("Error getting list of registrations, status=%d".formatted(exchange.getStatusCode().value()));
        }
    }

    public List<RegistrationDto> getRegistrations(String patientName) {
        var uri = baseUri.resolve(REGISTRATION_PATH + "/" + patientName);
        var exchange = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<RegistrationDto>>() {
                });
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return exchange.getBody();
        } else {
            throw new RestClientException("Error getting registration for patient=%s, status=%d".formatted(patientName, exchange.getStatusCode().value()));
        }
    }

    public Integer getCountOfRegistrationsByDoctorAndDate(String doctorName, LocalDate date) {
        var uri = baseUri.resolve(REGISTRATION_PATH + "?doctorName=" + doctorName.replace(" ", "%20") + "&date=" + date);
        var exchange = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {
                });
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return exchange.getBody();
        } else {
            throw new RestClientException("Error getting registration for doctor=%s, status=%d".formatted(doctorName, exchange.getStatusCode().value()));
        }
    }
}
