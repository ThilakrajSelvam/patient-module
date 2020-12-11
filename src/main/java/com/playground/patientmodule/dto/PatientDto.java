package com.playground.patientmodule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.playground.patientmodule.enums.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * PatientDto is the dto for Patient Entity
 *
 * @author thilak
 * @created 12/4/20
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PatientDto implements Serializable {

    private static final long serialVersionUID = -1045132217012089475L;

    private UUID patientId;

    @Email
    @Size(max = 50, message = "Email should not be greater than 50 characters")
    private String email;

    @Size(max = 50, message = "First Name should not be greater than 50 characters")
    private String firstName;

    @Size(max = 50, message = "Last Name should not be greater than 50 characters")
    private String lastName;

    private Integer age;

    private Gender gender;

    @Size(max = 100, message = "Address Line 1 should not be greater than 100 characters")
    private String addressLine1;

    @Size(max = 100, message = "Address Line 2 should not be greater than 100 characters")
    private String addressLine2;

    @Size(max = 10, message = "Mobile No should not be greater than 10 characters")
    private String mobileNo;


}
