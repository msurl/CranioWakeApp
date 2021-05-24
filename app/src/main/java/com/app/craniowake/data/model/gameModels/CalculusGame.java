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
 * Model of the Calculus Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "calculus_test_table")
public class CalculusGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long calcGameId;
    private String equation;
    private boolean answer;

    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public CalculusGame(boolean answer, String equation, long fkOperationId) {
        super();
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.equation = equation;
    }

    @Ignore
    public CalculusGame(boolean answer, String equation, double stimulation, long fkOperationId) {
        super(stimulation);
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.equation = equation;
    }
}
