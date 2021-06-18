package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
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
    private String text;
    private int mistakeCounter;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public ReadGame(String text, long fkOperationId) {
        this(text, 0, fkOperationId);
    }

    @Ignore
    public ReadGame(String text, int mistakeCounter, long fkOperationId) {
        super();
        this.text = text;
        this.mistakeCounter = mistakeCounter;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public ReadGame(String text, int mistakeCounter, double stimulation, long fkOperationId) {
        super(stimulation);
        this.text = text;
        this.mistakeCounter = mistakeCounter;
        this.fkOperationId = fkOperationId;
    }

    public void incrementMistakes() {
        mistakeCounter++;
    }

    public void decrementMistakes() {
        if(mistakeCounter > 0)
        {
            mistakeCounter--;
        }
    }
}
