package com.app.craniowake.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Model of the Patient
 * the attribute caseNumber is unique
 */
@Data
@Entity(indices = {@Index(value = {"caseNumber"}, unique = true)}, tableName = "patient_table")
public class Patient{

    @PrimaryKey(autoGenerate = true)
    private long patientId;

    private Long caseNumber;
    private String lastname;
    private String firstname;
    private String birthDate;
    private LocalDateTime dateTime;
    private String sex;
    @Embedded
    private University university;

    public Patient(Long caseNumber, String lastname, String firstname, String birthDate, String sex, University university) {
        this.caseNumber = caseNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.university = university;
        this.dateTime = LocalDateTime.now();
    }

    public Patient(Long caseNumber, String lastname, String firstname, String birthDate, String sex, String universityName, String universityLocation) {
        this.caseNumber = caseNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.university = new University(universityName, universityLocation);
        this.dateTime = LocalDateTime.now();
    }



    public Patient() {
        this.university = new University();
        this.dateTime = LocalDateTime.now();
    }

    public long getPatientId() {
        return patientId;
    }
}
