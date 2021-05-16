package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.stimulation.ThresholdStimulation;
import com.app.craniowake.data.model.stimulation.ThresholdTest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThresholdTestWithStimulations {
    @Embedded
    private ThresholdTest thresholdTest;

    @Relation(
            parentColumn = "thresholdId",
            entityColumn = "stimulationId"
    )
    public List<ThresholdStimulation> stimulations;
}
