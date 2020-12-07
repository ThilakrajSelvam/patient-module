package com.playground.patientmodule.exception;

/**
 * DuplicateMobileNoException is thrown to indicate that mobile number already exists in database
 *
 * @author thilak
 * @created 12/4/20
 */
public class DuplicateMobileNoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateMobileNoException() {
        super("Mobile Number Already Exists");
    }
}
