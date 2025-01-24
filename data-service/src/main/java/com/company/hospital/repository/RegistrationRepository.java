package com.company.hospital.repository;

import java.time.LocalDate;
import java.util.List;

import com.company.hospital.dao.Registration;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<Registration, String> {

    List<Registration> findAll();

    List<Registration> findAllByPatientFullName(String patientFullName);

    @Query("SELECT count(*) FROM Registration r LEFT JOIN Doctor d ON r.id = d.id WHERE d.id = ?1 AND r.visitDate = ?2")
    Integer findCountOfRegistrationByDoctorAndDate(Long doctorId, LocalDate date);

    @Query(value = "SELECT patient_full_name, count(*) as count FROM registration GROUP BY patient_full_name ORDER BY count DESC", nativeQuery = true)
    List<String> findTopPatients(Limit limit);
}
