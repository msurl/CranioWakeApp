package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Picture Test
 */
@Data
@Entity(tableName = "picture_test_table")
public class PictureGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int pictureId;
    private String pictureName;
    private boolean answer;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public PictureGame(String pictureName, boolean answer, int fkOperationId) {
        this.pictureName = pictureName;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
