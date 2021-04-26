package com.app.craniowake.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Operation
 * the attribute dateTime is unique to distinguish different operations with LiveData
 */
@Data
@Entity(indices = {@Index(value = {"dateTime"}, unique = true)}, tableName = "operation_table")
public class Operation implements CurrentDateTimable{

    @PrimaryKey(autoGenerate = true)
    private int operationId;

    private String brainarea;
    private String operationMode;
    private String dateTime;
    @ForeignKey
            (entity = Patient.class,
                    parentColumns = "patientId",
                    childColumns = "fkPatientId",
                    onDelete = CASCADE,
                    onUpdate = CASCADE
            )
    private int fkPatientId;

    public Operation(String brainarea, String operationMode, int fkPatientId) {
        this.brainarea = brainarea;
        this.operationMode = operationMode;
        this.fkPatientId = fkPatientId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
