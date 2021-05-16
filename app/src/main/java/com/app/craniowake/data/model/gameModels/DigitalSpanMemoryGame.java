package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Digital Span Memory Test
 */
@Data
@Entity(tableName = "digital_span_test_table")
public class DigitalSpanMemoryGame {

    @PrimaryKey(autoGenerate = true)
    private long digitalSpanMemoryId;
    private boolean answer;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;


    public DigitalSpanMemoryGame(boolean answer, long fkOperationId) {
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
