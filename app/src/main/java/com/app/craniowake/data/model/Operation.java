package com.app.craniowake.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Operation
 * the attribute dateTime is unique to distinguish different operations with LiveData
 */
@Data
@Entity(indices = {@Index(value = {"dateTime"}, unique = true)}, tableName = "operation_table")
public class Operation{

    @PrimaryKey(autoGenerate = true)
    private long operationId;

    private String brainarea;
    private String operationMode;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Patient.class,
                    parentColumns = "patientId",
                    childColumns = "fkPatientId",
                    onDelete = CASCADE,
                    onUpdate = CASCADE
            )
    private long fkPatientId;

    public Operation(String brainarea, String operationMode, long fkPatientId) {
        this.brainarea = brainarea;
        this.operationMode = operationMode;
        this.fkPatientId = fkPatientId;
        this.dateTime = LocalDateTime.now();
    }
}
