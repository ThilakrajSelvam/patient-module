package com.playground.patientmodule.controller;

import com.playground.patientmodule.dto.PatientDto;
import com.playground.patientmodule.service.PatientService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * PatientController provides rest endpoints of patient details
 *
 * @author thilak
 * @created 12/4/20
 */
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    /**
     * Saves the patient details
     *
     * @param patientDto
     * @return
     */
    @PostMapping
    @ApiOperation(value = "Saves the patient details")
    @ResponseStatus(value = HttpStatus.CREATED)
    public PatientDto createPatient(@RequestBody PatientDto patientDto) {
        return patientService.createPatient(patientDto);
    }

    /**
     * Retrieves the patient details
     *
     * @param patientId
     * @return
     */
    @GetMapping(path = "/{patientId}")
    @ApiOperation(value = "Retrieves the patient details")
    public PatientDto viewPatient(@PathVariable UUID patientId) {
        return patientService.viewPatient(patientId);
    }

    /**
     * Updates the patient details
     *
     * @param patientDto
     * @return
     */
    @PutMapping
    @ApiOperation(value = "Updates the patient details")
    public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
        return patientService.updatePatient(patientDto);
    }

    /**
     * Deletes the patient details
     *
     * @param patientId
     * @return
     */
    @DeleteMapping(path = "/{patientId}")
    @ApiOperation(value = "Deletes the patient details")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removePatient(@PathVariable UUID patientId) {
        patientService.removePatient(patientId);
    }

    /**
     * Retrieves all the patients details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param recordsPerPage
     * @return
     */
    @GetMapping
    @ApiOperation(value = "Retrieves all the patients details matching the given condition")
    public List<PatientDto> getAllPatients(@RequestParam(name = "orderby", defaultValue = "firstName") String orderBy,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "recordsperpage", defaultValue = "10") int recordsPerPage) {
        return patientService.getAllPatients(orderBy, page, recordsPerPage);
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
    @GetMapping("/search")
    @ApiOperation(value = "Retrieves all the patients details matching the given condition")
    public List<PatientDto> search(
            @RequestParam(name = "text") String text,
            @RequestParam(name = "orderby", defaultValue = "firstName") String orderBy,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "recordsperpage", defaultValue = "10") int recordsPerPage) {

        if (text.isEmpty() || text == null)
            return getAllPatients(orderBy, page, recordsPerPage);

        return patientService.search(text, orderBy, page, recordsPerPage);

    }

    /**
     * Rerieves the patientId
     *
     * @return
     */
    @GetMapping(path = "/patientid")
    public UUID getPatientId() {
        return patientService.getPatientId();
    }

}
