package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Trail Making Test
 */
@Data
@Entity(tableName = "trail_making_test_table")
public class TrailMakingGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int trailwayId;
    private boolean correctAnswer;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public TrailMakingGame(boolean correctAnswer, int fkOperationId) {
        this.correctAnswer = correctAnswer;
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}


