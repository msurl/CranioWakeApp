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
 * Model of the Digital Span Memory Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "digital_span_test_table")
public class DigitalSpanMemoryGame extends Game{

    @PrimaryKey(autoGenerate = true)
    private long digitalSpanMemoryId;
    private boolean answer;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;


    public DigitalSpanMemoryGame(boolean answer, long fkOperationId) {
        super();
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public DigitalSpanMemoryGame(boolean answer, double stimulation, long fkOperationId) {
        super(stimulation);
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }
}
