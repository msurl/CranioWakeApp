package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Picture Test
 */
@Data
@Entity(tableName = "picture_test_table")
public class PictureGame {

    @PrimaryKey(autoGenerate = true)
    private long pictureId;
    private String pictureName;
    private boolean answer;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public PictureGame(String pictureName, boolean answer, long fkOperationId) {
        this.pictureName = pictureName;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
