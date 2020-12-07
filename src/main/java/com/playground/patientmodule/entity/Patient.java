package com.playground.patientmodule.entity;

import com.playground.patientmodule.converter.GenderConverter;
import com.playground.patientmodule.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Patient is a JPA Entity
 *
 * @author thilak
 * @created 12/4/20
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "patient_mst")
public class Patient {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID patientId;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    private Integer age;

    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(length = 100)
    private String addressLine1;

    @Column(length = 100)
    private String addressLine2;

    @NonNull
    @Column(length = 13, unique = true)
    private String mobileNo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
