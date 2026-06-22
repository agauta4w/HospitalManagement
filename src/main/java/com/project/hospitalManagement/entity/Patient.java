package com.project.hospitalManagement.entity;

import com.project.hospitalManagement.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@Table(
        name= "patient_tbl",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique", columnNames = "email")
        },
        indexes = {
                @Index(name = "idx_date_of_birth", columnList = "dateOfBirth")

        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ToString.Exclude
    private LocalDate dateOfBirth;
    private String email;
    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
}
