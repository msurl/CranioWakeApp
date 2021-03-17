package com.app.craniowake.data.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.Data;

/**
 * Model of the Patient
 * the attribute caseNumber is unique
 */
@Data
@Entity(indices = {@Index(value = {"caseNumber"}, unique = true)}, tableName = "patient_table")
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int patientId;

    private long caseNumber;
    private String lastname;
    private String firstname;
    private String birthDate;
    private String dateTime;
    private String sex;

    public Patient(long caseNumber, String lastname, String firstname, String birthDate, String sex) {
        this.caseNumber = caseNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthDate = birthDate;
        this.sex = sex;
        setCurrentDateTime();
    }

    /**
     * returns current Date and Time when called.
     */
    private void setCurrentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        this.dateTime = formatDateTime(localDateTime);
    }

    /**
     * Entity of the Calculus Test
     *
     * @param localDateTime formats generated datetime to: JJJJ-MM-DDT00:00:00.000
     */
    private String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return localDateTime.format(formatter);
    }

    public int getPatientId() {
        return patientId;
    }


    public long getCaseNumber() {
        return caseNumber;
    }

}
