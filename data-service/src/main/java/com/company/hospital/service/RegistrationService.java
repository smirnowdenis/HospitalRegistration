package com.company.hospital.service;

import java.time.LocalDate;
import java.util.List;

import com.company.hospital.dao.Registration;
import com.company.hospital.repository.DoctorRepository;
import com.company.hospital.repository.RegistrationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    private final DoctorRepository doctorRepository;

    private final ObjectMapper objectMapper;

    public List<Registration> getRegistrations() {
        log.info("Executing getRegistrations");
        var registrations = registrationRepository.findAll();
        log.info("Finished executing getRegistrations");
        return registrations;
    }

    public void createRegistration(String message) {
        Registration registration;
        try {
            registration = objectMapper.readValue(message, Registration.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var doctorOptional = doctorRepository.findByFullName(registration.getDoctor().getFullName());
        if (doctorOptional.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }
        registration.setDoctor(doctorOptional.get());
        log.info("Start of saving registration");
        registrationRepository.save(registration);
        log.info("Finished of saving registration");
    }

    public List<Registration> getRegistrations(String patientName) {
        log.info("Executing getRegistrations for patient={}", patientName);
        var registrations = registrationRepository.findAllByPatientFullName(patientName);
        log.info("Finished executing getRegistrations for patient={}", patientName);
        return registrations;
    }

    public Integer getCountOfRegistrationsByDoctorAndDate(String doctorFullName, LocalDate date) {
        log.info("Executing getCountOfRegistrationsByDoctorAndDate for doctor={}", doctorFullName);
        var doctorOptional = doctorRepository.findByFullName(doctorFullName);
        if (doctorOptional.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }
        var countOfRegistration = registrationRepository.findCountOfRegistrationByDoctorAndDate(doctorOptional.get().getId(), date);
        log.info("Finished executing getCountOfRegistrationsByDoctorAndDate for doctor={}", doctorFullName);
        return countOfRegistration;
    }

    public List<String> findTop10Patients() {
        log.info("Executing findTop10Patients");
        var patients = registrationRepository.findTopPatients(Limit.of(10));
        log.info("Finished findTop10Patients");
        return patients;
    }


}
