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
 * Model of the Stroop Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "stroop_test_table")
public class StroopGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long stroopId;
    private String ink;
    private boolean answer;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public StroopGame(String ink, boolean answer, long fkOperationId) {
        super();
        this.ink = ink;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public StroopGame(String ink, boolean answer, double stimulation, long fkOperationId) {
        super(stimulation);
        this.ink = ink;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }
}
