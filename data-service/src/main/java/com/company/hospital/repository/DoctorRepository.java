package com.company.hospital.repository;

import java.util.List;
import java.util.Optional;

import com.company.hospital.dao.Doctor;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    Optional<Doctor> findByFullName(String fullName);

    @Query(value = "SELECT d.full_name, r.count FROM doctor d " +
            "LEFT JOIN (SELECT doctor_id, count(*) as count FROM registration GROUP BY doctor_id ORDER BY count DESC) r " +
            "ON r.doctor_id = d.id", nativeQuery = true)
    List<String> findTopDoctors(Limit limit);
}
