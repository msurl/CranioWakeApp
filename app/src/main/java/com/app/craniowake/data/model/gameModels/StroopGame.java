package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Stroop Test
 */
@Data
@Entity(tableName = "stroop_test_table")
public class StroopGame {

    @PrimaryKey(autoGenerate = true)
    private long stroopId;
    private String ink;
    private boolean answer;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public StroopGame(String ink, boolean answer, long fkOperationId) {
        this.ink = ink;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
