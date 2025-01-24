package com.company.hospital.controller;

import java.util.List;

import com.company.hospital.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patient/top")
    public ResponseEntity<List<String>> getTop10Patients() {
        log.info("Getting top 10 patients");
        var top10Patients = patientService.getTop10Patients();
        log.info("Finished getting top 10 patients");
        return ResponseEntity.ok(top10Patients);
    }
}
