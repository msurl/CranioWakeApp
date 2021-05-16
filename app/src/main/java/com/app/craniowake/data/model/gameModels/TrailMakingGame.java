package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Trail Making Test
 */
@Data
@Entity(tableName = "trail_making_test_table")
public class TrailMakingGame {

    @PrimaryKey(autoGenerate = true)
    private long trailwayId;
    private boolean correctAnswer;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public TrailMakingGame(boolean correctAnswer, long fkOperationId) {
        this.correctAnswer = correctAnswer;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}


