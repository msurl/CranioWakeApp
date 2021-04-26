package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Read Test
 */
@Data
@Entity(tableName = "read_test_table")
public class ReadGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int readId;
    private int mistakeCounter;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public ReadGame(int mistakeCounter, int fkOperationId) {
        this.mistakeCounter = mistakeCounter;
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
