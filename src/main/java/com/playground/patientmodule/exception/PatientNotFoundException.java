package com.playground.patientmodule.exception;

/**
 * PatientNotFoundException is thrown to indicate that patient does not exist in database
 *
 * @author thilak
 * @created 12/4/20
 */
public class PatientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PatientNotFoundException() {
        super("Patient not found");
    }

}
