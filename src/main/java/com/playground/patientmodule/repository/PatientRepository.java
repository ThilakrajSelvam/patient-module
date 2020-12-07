package com.playground.patientmodule.repository;

import com.playground.patientmodule.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * PatientRepository provides methods to interact with database table
 *
 * @author thilak
 * @created 12/4/20
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    /**
     * Check whether the mobile no already exists in database
     *
     * @param mobileNo
     * @return
     */
    Boolean existsByMobileNo(String mobileNo);

}
