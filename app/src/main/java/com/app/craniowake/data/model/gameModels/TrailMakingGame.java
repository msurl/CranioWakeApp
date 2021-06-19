package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Trail Making Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "trail_making_test_table")
public class TrailMakingGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long trailwayId;
    private boolean correctAnswer;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public TrailMakingGame(boolean correctAnswer, long fkOperationId) {
        super();
        this.correctAnswer = correctAnswer;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public TrailMakingGame(boolean correctAnswer, double stimulation, String stimulationType, long fkOperationId) {
        super(stimulation, stimulationType);
        this.correctAnswer = correctAnswer;
        this.fkOperationId = fkOperationId;
    }
}


