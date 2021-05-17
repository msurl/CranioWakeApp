package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Four Quadrant Test
 */
@Data
@Entity(tableName = "four_square_test_table")
public class FourSquareGame {

    @PrimaryKey(autoGenerate = true)
    private long FourSquareId;
    private String pictureName;
    private boolean answerSquare1;
    private boolean answerSquare2;
    private boolean answerSquare3;
    private boolean answerSquare4;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public FourSquareGame(String pictureName, boolean answerSquare1, boolean answerSquare2, boolean answerSquare3, boolean answerSquare4, long fkOperationId) {
        this.pictureName = pictureName;
        this.answerSquare1 = answerSquare1;
        this.answerSquare2 = answerSquare2;
        this.answerSquare3 = answerSquare3;
        this.answerSquare4 = answerSquare4;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
