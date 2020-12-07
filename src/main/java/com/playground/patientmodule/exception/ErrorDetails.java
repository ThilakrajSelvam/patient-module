package com.playground.patientmodule.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * ErrorDetails holds the exception information
 *
 * @author thilak
 * @created 12/4/20
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
