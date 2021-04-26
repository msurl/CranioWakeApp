package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the PPT Test
 */
@Data
@Entity(tableName = "pptt_test_table")
public class PpttGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int ppttId;
    private boolean answer;
    private String pictureName;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public PpttGame(String pictureName, boolean answer, int fkOperationId) {
        this.pictureName = pictureName;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
