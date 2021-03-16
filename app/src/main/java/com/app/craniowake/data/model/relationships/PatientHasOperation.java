package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.Patient;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class PatientHasOperation {
    @Embedded
    public Patient patient;
    @Relation(
            parentColumn = "patientId",
            entityColumn = "operationId"
    )
    public List<Operation> operationList;

    public PatientHasOperation(Patient patient, List<Operation> operationList) {
        this.patient = patient;
        this.operationList = operationList;
    }
}
