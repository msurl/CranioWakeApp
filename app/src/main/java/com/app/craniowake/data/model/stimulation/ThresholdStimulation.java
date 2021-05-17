package com.app.craniowake.data.model.stimulation;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(tableName = "threshold_stimulation_table")
@Data
public class ThresholdStimulation {

    private static String TYPE_MOTOR = "motor";
    private static String TYPE_SPEECH = "speech";

    @PrimaryKey(autoGenerate = true)
    private long stimulationId;
    @ForeignKey
            (entity = ThresholdTest.class,
                    parentColumns = "thresholdId",
                    childColumns = "thresholdId",
                    onDelete = ForeignKey.CASCADE
            )
    private long thresholdId;

    private LocalDateTime dateTime;

    private String charge;

    private String type;

    public ThresholdStimulation(String charge, String type) {
        this.charge = charge;
        dateTime = LocalDateTime.now();
    }

    public static ThresholdStimulation motorStimulationOf(String charge) {
        return new ThresholdStimulation(charge, TYPE_MOTOR);
    }

    public static ThresholdStimulation speechStimulationOf(String charge) {
        return new ThresholdStimulation(charge, TYPE_SPEECH);
    }
}
