package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Reaction Test
 */
@Data
@Entity(tableName = "reaction_test_table")
public class ReactionGame {

    @PrimaryKey(autoGenerate = true)
    private long reactionId;
    private int milisec;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public ReactionGame(int milisec, long fkOperationId) {
        this.milisec = milisec;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
