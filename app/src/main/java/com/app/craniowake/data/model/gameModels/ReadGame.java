package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Read Test
 */
@Data
@Entity(tableName = "read_test_table")
public class ReadGame {

    @PrimaryKey(autoGenerate = true)
    private long readId;
    private int mistakeCounter;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public ReadGame(int mistakeCounter, long fkOperationId) {
        this.mistakeCounter = mistakeCounter;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
