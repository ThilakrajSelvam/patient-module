package com.playground.patientmodule.converter;

import com.playground.patientmodule.enums.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author thilak
 * @created 12/7/20
 */
@Converter
public class GenderConverter implements AttributeConverter<Gender, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Gender attribute) {
        if (attribute == null)
            return Gender.MALE.getGenderValue();
        return attribute.getGenderValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer genderValue) {
        if (genderValue == null)
            return Gender.MALE;
        return Stream.of(Gender.values())
                .filter(gender -> genderValue.equals(gender.getGenderValue()))
                .findFirst()
                .orElse(Gender.MALE);
    }
}
