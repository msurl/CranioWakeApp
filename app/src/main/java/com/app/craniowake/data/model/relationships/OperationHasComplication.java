package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Complication;
import com.app.craniowake.data.model.Operation;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasComplication {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "complicationId"
    )
    public List<Complication> complications;

    public OperationHasComplication(Operation operation, List<Complication> complications) {
        this.operation = operation;
        this.complications = complications;
    }
}
