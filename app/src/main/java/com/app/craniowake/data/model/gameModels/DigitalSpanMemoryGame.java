package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Digital Span Memory Test
 */
@Data
@Entity(tableName = "digital_span_test_table")
public class DigitalSpanMemoryGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int digitalSpanMemoryId;
    private boolean answer;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;


    public DigitalSpanMemoryGame(boolean answer, int fkOperationId) {
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
