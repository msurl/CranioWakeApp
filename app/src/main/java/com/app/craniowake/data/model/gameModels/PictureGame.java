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
 * Model of the Picture Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "picture_test_table")
public class PictureGame extends Game{

    @PrimaryKey(autoGenerate = true)
    private long pictureId;
    private String pictureName;
    private boolean face;
    private boolean answer;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public PictureGame(String pictureName, boolean face, boolean answer, long fkOperationId) {
        super();
        this.face = face;
        this.pictureName = pictureName;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public PictureGame(String pictureName, boolean face, boolean answer, double stimulation, String stimulationType, long fkOperationId) {
        super(stimulation, stimulationType);
        this.face = face;
        this.pictureName = pictureName;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }
}
