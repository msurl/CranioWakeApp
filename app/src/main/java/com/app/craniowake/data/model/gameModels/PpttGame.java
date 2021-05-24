package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the PPT Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "pptt_test_table")
public class PpttGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long ppttId;
    private boolean answer;
    private String pictureName;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public PpttGame(String pictureName, boolean answer, long fkOperationId) {
        super();
        this.pictureName = pictureName;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }

    public PpttGame(String pictureName, boolean answer, double stimulation, long fkOperationId) {
        super(stimulation);
        this.pictureName = pictureName;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }
}
