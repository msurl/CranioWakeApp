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
 * Model of the Reaction Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "reaction_test_table")
public class ReactionGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long reactionId;
    private int milisec;

    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public ReactionGame(int milisec, long fkOperationId) {
        super();
        this.milisec = milisec;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public ReactionGame(int milisec, double stimulation, long fkOperationId) {
        super(stimulation);
        this.milisec = milisec;
        this.fkOperationId = fkOperationId;
    }
}
