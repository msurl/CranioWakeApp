package com.app.craniowake.data.model.stimulation;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

// TODO: Namen sinnvoll ändern!
@Data
@Entity(tableName = "threshold_test_table")
public class ThresholdTest {

    @PrimaryKey(autoGenerate = true)
    private long thresholdId;

    private String motorFunctionStimulationArea;
    private String clinicalEMGBoth; // TODO: Unbedingt abklären, wie dieser String benannt werden soll

    private String responseInMuscle;

    @Ignore
    private List<ThresholdStimulation> stimulations;

    private LocalDateTime dateTime;

    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = ForeignKey.CASCADE
            )
    private long fkOperationId;


    public ThresholdTest(String motorFunctionStimulationArea, String clinicalEMGBoth, String responseInMuscle) {
        this.motorFunctionStimulationArea = motorFunctionStimulationArea;
        this.clinicalEMGBoth = clinicalEMGBoth;
        this.responseInMuscle = responseInMuscle;
        this.stimulations = new ArrayList<>();
        this.dateTime = LocalDateTime.now();
    }

    public void addMotorStimulation(String charge) {
        this.stimulations.add(ThresholdStimulation.motorStimulationOf(charge));
    }

    public void addSpeechStimulation(String charge) {
        this.stimulations.add(ThresholdStimulation.motorStimulationOf(charge));
    }

    public ThresholdTest withMotorStimulation(String charge) {
        addMotorStimulation(charge);
        return this;
    }

    public ThresholdTest withSpeechStimulation(String charge) {
        addSpeechStimulation(charge);
        return this;
    }
}
