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

// TODO: Namen sinnvoll anpassen! Priority: MEDIUM
@Data
@Entity(tableName = "verification_test_table")
public class VerificationTest {

    @PrimaryKey(autoGenerate = true)
    private long verificationId;

    private String motorFunctionStimulationType;

    private String motorFunctionStimulationArea;
    private String clinicalEMGBoth;

    private String responseInMuscle;

    private String speechAndLanguageStimulationArea;


    private LocalDateTime dateTime;

    private static final String EMPTY_STRING = "";

    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationid",
                    onDelete = ForeignKey.CASCADE
            )
    private int fkOperationId;

    @Ignore
    private List<VerificationStimulation> stimulations;

    public VerificationTest(String motorFunctionStimulationType, String motorFunctionStimulationArea, String clinicalEMGBoth, String responseInMuscle,
                            String speechAndLanguageStimulationArea) {
        this.motorFunctionStimulationType = motorFunctionStimulationType;
        this.motorFunctionStimulationArea = motorFunctionStimulationArea;
        this.clinicalEMGBoth = clinicalEMGBoth;
        this.responseInMuscle = responseInMuscle;
        this.speechAndLanguageStimulationArea = speechAndLanguageStimulationArea;
        this.dateTime = LocalDateTime.now();
        this.stimulations = new ArrayList<>();
    }

    public void addMotorStimulation(String charge) {
        this.stimulations.add(VerificationStimulation.motorStimulationOf(charge));
    }

    public void addSpeechStimulation(String charge) {
        this.stimulations.add(VerificationStimulation.speechStimulationOf(charge));
    }

    public VerificationTest withMotorStimulation(String charge) {
        this.addMotorStimulation(charge);
        return this;
    }

    public VerificationTest withSpeechStimulation(String charge) {
        this.addSpeechStimulation(charge);
        return this;
    }
}
