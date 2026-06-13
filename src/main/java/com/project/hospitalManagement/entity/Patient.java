package com.project.hospitalManagement.entity;

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
                @Index(name = "email_index", columnList = "email")

        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDate dateOfBirth;
    private String email;
    private String gender;
}
