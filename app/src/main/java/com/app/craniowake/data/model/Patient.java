package com.app.craniowake.data.model;

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

    private long caseNumber;
    private String lastname;
    private String firstname;
    private String birthDate;
    private LocalDateTime dateTime;
    private String sex;

    public Patient(long caseNumber, String lastname, String firstname, String birthDate, String sex) {
        this.caseNumber = caseNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.dateTime = LocalDateTime.now();
    }

    public Patient() {
        this.dateTime = LocalDateTime.now();
    }

    public long getPatientId() {
        return patientId;
    }

    public long getCaseNumber() {
        return caseNumber;
    }
}
