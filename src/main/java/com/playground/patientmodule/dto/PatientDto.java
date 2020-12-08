package com.playground.patientmodule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.playground.patientmodule.enums.Gender;
import lombok.Data;

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

    private String email;

    private String firstName;

    private String lastName;

    private Integer age;

    private Gender gender;

    private String addressLine1;

    private String addressLine2;

    private String mobileNo;


}
