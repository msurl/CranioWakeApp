package com.app.craniowake.data.model.stimulation;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(tableName = "verification_stimulation_table")
@Data
@NoArgsConstructor
public class VerificationStimulation {

    private static String TYPE_MOTOR = "motor";
    private static String TYPE_SPEECH = "speech";


    @PrimaryKey(autoGenerate = true)
    private long stimulationId;

    @ForeignKey
            (entity = VerificationTest.class,
                    parentColumns = "verificationId",
                    childColumns = "verificationId",
                    onDelete = ForeignKey.CASCADE
            )
    private long verificationId;

    private LocalDateTime dateTime;

    private String charge;

    private String type;

    private VerificationStimulation(String charge, String type) {
        this.charge = charge;
        this.type = type;
        this.dateTime = LocalDateTime.now();
    }

    public static VerificationStimulation motorStimulationOf(String charge) {
        return new VerificationStimulation(charge, TYPE_MOTOR);
    }

    public static VerificationStimulation speechStimulationOf(String charge) {
        return new VerificationStimulation(charge, TYPE_SPEECH);
    }
}
