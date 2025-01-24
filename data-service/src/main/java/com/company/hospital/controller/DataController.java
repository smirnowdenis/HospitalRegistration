package com.company.hospital.controller;

import java.time.LocalDate;
import java.util.List;

import com.company.hospital.dao.Registration;
import com.company.hospital.service.DoctorService;
import com.company.hospital.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DataController {

    private final RegistrationService registrationService;
    private final DoctorService doctorService;

    @GetMapping("/registration/")
    public ResponseEntity<List<Registration>> getRegistrations() {
        var registrations = registrationService.getRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registration/{patientName}")
    public ResponseEntity<List<Registration>> getRegistrationsByPatientName(@PathVariable String patientName) {
        var registrations = registrationService.getRegistrations(patientName);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/registration")
    public ResponseEntity<Integer> getCountOfRegistrationsByDoctorAndDate(@RequestParam String doctorName, @RequestParam LocalDate date) {
        var countOfRegistrations = registrationService.getCountOfRegistrationsByDoctorAndDate(doctorName, date);
        return ResponseEntity.ok(countOfRegistrations);
    }

    @GetMapping("/doctor/top")
    public ResponseEntity<List<String>> getTop10Doctors() {
        var top10Doctors = doctorService.findTop10Doctors();
        return ResponseEntity.ok(top10Doctors);
    }

    @GetMapping("/patient/top")
    public ResponseEntity<List<String>> getTop10Patients() {
        var top10Patients = registrationService.findTop10Patients();
        return ResponseEntity.ok(top10Patients);
    }
}
