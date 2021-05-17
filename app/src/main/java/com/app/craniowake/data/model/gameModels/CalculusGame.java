package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Calculus Test
 */
@Data
@Entity(tableName = "calculus_test_table")
public class CalculusGame {

    @PrimaryKey(autoGenerate = true)
    private long calcGameId;
    private String equation;
    private boolean answer;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public CalculusGame(boolean answer, String equation, long fkOperationId) {
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.equation = equation;
        this.dateTime = LocalDateTime.now();
    }
}
