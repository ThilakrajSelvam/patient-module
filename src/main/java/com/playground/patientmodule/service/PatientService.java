package com.playground.patientmodule.service;

import com.playground.patientmodule.dto.PatientDto;

import java.util.List;
import java.util.UUID;

/**
 * PatientService provides the services for patients
 *
 * @author thilak
 * @created 12/4/20
 */
public interface PatientService {

    /**
     * Saves the patient details
     *
     * @param patientDto
     * @return
     */
    PatientDto createPatient(PatientDto patientDto);

    /**
     * Retrieves the patient details
     *
     * @param patientId
     * @return
     */
    PatientDto viewPatient(UUID patientId);

    /**
     * Updates the patient details
     *
     * @param patientDto
     * @return
     */
    PatientDto updatePatient(PatientDto patientDto);

    /**
     * Deletes the patient details
     *
     * @param patientId
     */
    void removePatient(UUID patientId);

    /**
     * Retrieves all the patients details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param recordsPerPage
     * @return
     */
    List<PatientDto> getAllPatients(String orderBy, int page, int recordsPerPage);

    /**
     * Retrieves all the patients details matching the given condition
     *
     * @param text
     * @param orderBy
     * @param page
     * @param recordsPerPage
     * @return
     */
    List<PatientDto> search(String text, String orderBy, int page, int recordsPerPage);

    /**
     * Rerieves the patientId
     *
     * @return
     */
    UUID getPatientId();

}
