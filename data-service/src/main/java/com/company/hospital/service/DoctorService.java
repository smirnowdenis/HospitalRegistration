package com.company.hospital.service;

import java.util.List;

import com.company.hospital.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<String> findTop10Doctors() {
        log.info("Executing findTop10Doctors");
        var doctors = doctorRepository.findTopDoctors(Limit.of(10));
        log.info("Finished findTop10Doctors");
        return doctors;
    }
}
