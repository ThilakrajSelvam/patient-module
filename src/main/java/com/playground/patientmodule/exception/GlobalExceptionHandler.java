package com.playground.patientmodule.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler handles the Custom Exception thrown by the services
 *
 * @author thilak
 * @created 12/4/20
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the UserNotFoundException
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(PatientNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

    }

    /**
     * Handles the DuplicateMobileNoException
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(DuplicateMobileNoException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateMobileNoException(DuplicateMobileNoException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println(ex.getBindingResult().getAllErrors());
        Map<String, String> errors = ex.getAllErrors().stream()
                .collect(Collectors.toMap((objectError -> ((FieldError) objectError).getField()), ObjectError::getDefaultMessage));
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

}
