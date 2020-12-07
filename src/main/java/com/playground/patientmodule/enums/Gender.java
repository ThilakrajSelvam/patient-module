package com.playground.patientmodule.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for Gender
 *
 * @author thilak
 * @created 12/4/20
 */
@RequiredArgsConstructor
@Getter
public enum Gender {
    MALE(0),
    FEMALE(1),
    OTHER(2);

    private final int genderValue;
}
