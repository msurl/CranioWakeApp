package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Calculus Test
 */
@Data
@Entity(tableName = "calculus_test_table")
public class CalculusGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int calcGameId;
    private String equation;
    private boolean answer;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public CalculusGame(boolean answer, String equation, int fkOperationId) {
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.equation = equation;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
