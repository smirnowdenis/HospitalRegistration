package com.company.hospital.controller;

import java.time.LocalDate;
import java.util.List;

import com.company.hospital.dto.RegistrationDto;
import com.company.hospital.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(value = "/registration/")
    public ResponseEntity<String> createRegistration(@RequestBody RegistrationDto registrationDto) {
        log.info("Creating new registration");
        try {
            registrationService.createRegistration(registrationDto);
        } catch (KafkaException exception) {
            log.error("Something went wrong", exception);
            return ResponseEntity.status(500).body("Error");
        }
        log.info("Registration created");
        return ResponseEntity.ok("Created");
    }

    @GetMapping("/registration/")
    public ResponseEntity<List<RegistrationDto>> getRegistrations() {
        log.info("Getting registrations");
        var registrations = registrationService.getRegistrations();
        log.info("Registrations retrieved");
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registration/{patientName}")
    public ResponseEntity<List<RegistrationDto>> getRegistrationsByPatientName(@PathVariable String patientName) {
        log.info("Getting registrations for patient={}", patientName);
        var registrations = registrationService.getRegistrations(patientName);
        log.info("Registrations retrieved for patient={}", patientName);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registration")
    public ResponseEntity<Integer> getCountOfRegistrationsByDoctorAndDate(@RequestParam String doctorName,
                                                                          @RequestParam LocalDate date) {
        log.info("Getting registrations for doctor={} and date={}", doctorName, date);
        var countOfRegistrations = registrationService.getCountOfRegistrationsByDoctorAndDate(doctorName, date);
        log.info("Registrations retrieved for doctor={} and date={}", doctorName, date);
        return ResponseEntity.ok(countOfRegistrations);
    }
}
