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
 * Model of the Read Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "read_test_table")
public class ReadGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long readId;
    private int mistakeCounter;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public ReadGame(int mistakeCounter, long fkOperationId) {
        super();
        this.mistakeCounter = mistakeCounter;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public ReadGame(int mistakeCounter, double stimulation, long fkOperationId) {
        super(stimulation);
        this.mistakeCounter = mistakeCounter;
        this.fkOperationId = fkOperationId;
    }
}
