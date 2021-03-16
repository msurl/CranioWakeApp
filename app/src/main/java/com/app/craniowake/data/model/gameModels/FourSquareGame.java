package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Four Quadrant Test
 */
@Data
@Entity(tableName = "four_square_table")
public class FourSquareGame {

    @PrimaryKey(autoGenerate = true)
    private int FourSquareId;
    private String pictureName;
    private boolean answerSquare1;
    private boolean answerSquare2;
    private boolean answerSquare3;
    private boolean answerSquare4;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public FourSquareGame(String pictureName, boolean answerSquare1, boolean answerSquare2, boolean answerSquare3, boolean answerSquare4, int fkOperationId) {
        this.pictureName = pictureName;
        this.answerSquare1 = answerSquare1;
        this.answerSquare2 = answerSquare2;
        this.answerSquare3 = answerSquare3;
        this.answerSquare4 = answerSquare4;
        this.fkOperationId = fkOperationId;
        setCurrentDateTime();
    }

    /**
     * returns current Date and Time when called.
     */
    private void setCurrentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        this.dateTime = formatDateTime(localDateTime);
    }

    /**
     * Entity of the Calculus Test
     * @param localDateTime formats generated datetime to: JJJJ-MM-DDT00:00:00.000
     */
    private String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return localDateTime.format(formatter);
    }

}
