package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.stimulation.ThresholdStimulation;
import com.app.craniowake.data.model.stimulation.VerificationTest;

import java.util.List;

import lombok.Data;

@Data
public class VerificationTestWithStimulations {
    @Embedded
    private VerificationTest verificationTest;

    @Relation(
            parentColumn = "verificationId",
            entityColumn = "stimulationId"
    )
    public List<ThresholdStimulation> stimulations;
}
