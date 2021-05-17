package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.stimulation.ThresholdTest;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
@AllArgsConstructor
public class OperationHasTresholdTest {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "thresholdId"
    )
    public List<ThresholdTest> thresholdTests;
}
