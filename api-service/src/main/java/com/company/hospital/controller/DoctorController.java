package com.company.hospital.controller;

import java.util.List;

import com.company.hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/doctor/top")
    public ResponseEntity<List<String>> getTop10Patients() {
        log.info("Getting top 10 doctors");
        var top10Doctors = doctorService.getTop10Doctors();
        log.info("Finished getting top 10 doctors");
        return ResponseEntity.ok(top10Doctors);
    }
}
