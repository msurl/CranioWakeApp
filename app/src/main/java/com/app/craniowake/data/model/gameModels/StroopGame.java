package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Stroop Test
 */
@Data
@Entity(tableName = "stroop_test_table")
public class StroopGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int stroopId;
    private String ink;
    private boolean answer;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public StroopGame(String ink, boolean answer, int fkOperationId) {
        this.ink = ink;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
