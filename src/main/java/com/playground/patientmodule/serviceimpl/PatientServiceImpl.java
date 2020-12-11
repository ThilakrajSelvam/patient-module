package com.playground.patientmodule.serviceimpl;

import com.playground.patientmodule.dto.PatientDto;
import com.playground.patientmodule.entity.Patient;
import com.playground.patientmodule.exception.DuplicateMobileNoException;
import com.playground.patientmodule.exception.PatientNotFoundException;
import com.playground.patientmodule.repository.PatientRepository;
import com.playground.patientmodule.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * PatientServiceImpl provides the services for patients
 *
 * @author thilak
 * @created 12/7/20
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    /**
     * Saves the patient details
     *
     * @param patientDto
     * @return
     */
    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);

        if (patientRepository.existsByMobileNo(patientDto.getMobileNo()))
            throw new DuplicateMobileNoException();

        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientDto.class);
    }

    /**
     * Retrieves the patient details
     *
     * @param patientId
     * @return
     */
    @Cacheable(key = "#patientId", value = "old_age_patients", unless = "#result.getAge() < 40")
    public PatientDto viewPatient(UUID patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);

        if (!patient.isPresent())
            throw new PatientNotFoundException();

        return modelMapper.map(patient.get(), PatientDto.class);

    }

    /**
     * Updates the patient details
     *
     * @param patientDto
     * @return
     */
    @CachePut(key = "#patientDto.patientId", value = "patients")
    public PatientDto updatePatient(PatientDto patientDto) {
        if (!patientRepository.existsById(patientDto.getPatientId()))
            throw new PatientNotFoundException();

        Patient patient = modelMapper.map(patientDto, Patient.class);
        patient = patientRepository.save(patient);

        return modelMapper.map(patient, PatientDto.class);
    }

    /**
     * Deletes the patient details
     *
     * @param patientId
     */
    @CacheEvict(key = "#patientId", value = "patients")
    public void removePatient(UUID patientId) {
        try {
            patientRepository.deleteById(patientId);
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException();
        }
    }

    /**
     * Retrieves all the patients details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param recordsPerPage
     * @return
     */
    public List<PatientDto> getAllPatients(String orderBy, int page, int recordsPerPage) {
        Pageable pageable = PageRequest.of(page, recordsPerPage, Sort.by(orderBy).ascending());
        List<Patient> patients = patientRepository.findAll(pageable).toList();
        return patients.stream().map(user -> modelMapper.map(user, PatientDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all the patients details matching the given condition
     *
     * @param text
     * @param orderBy
     * @param page
     * @param recordsPerPage
     * @return
     */
    public List<PatientDto> search(String text, String orderBy, int page, int recordsPerPage) {
        Pageable pageable = PageRequest.of(page, recordsPerPage, Sort.by(orderBy).ascending());
        Patient user = new Patient();
        user.setEmail(text);
        user.setFirstName(text);
        user.setLastName(text);
        user.setMobileNo(text);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("email", match -> match.contains())
                .withMatcher("firstName", match -> match.contains())
                .withMatcher("lastName", match -> match.contains())
                .withMatcher("mobileNo", match -> match.contains());
        Example<Patient> example = Example.of(user, matcher);
        List<Patient> patients = patientRepository.findAll(example, pageable).toList();
        return patients.stream().map(userEntity -> modelMapper.map(userEntity, PatientDto.class))
                .collect(Collectors.toList());

    }

    /**
     * Rerieves the patientId
     *
     * @return
     */
    public UUID getPatientId() {
        return patientRepository.findAll().get(0).getPatientId();
    }

}
