package com.company.hospital.service;

import java.net.URI;
import java.util.List;

import com.company.hospital.config.ApplicationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {

    private final RestTemplate restTemplate;

    private static final String PROTOCOL = "http://";
    private static final String PATIENT_PATH = "/patient";

    private final URI baseUri;

    public PatientService(RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.baseUri = URI.create(PROTOCOL + applicationProperties.getUrl());
    }

    public List<String> getTop10Patients() {
        var uri = baseUri.resolve(PATIENT_PATH + "/top");
        var exchange = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        });
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return exchange.getBody();
        } else {
            throw new RestClientException("Error getting top 10 patients, status=%d".formatted(exchange.getStatusCode().value()));
        }
    }

}
