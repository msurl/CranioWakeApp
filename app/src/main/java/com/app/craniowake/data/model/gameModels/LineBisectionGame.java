package com.app.craniowake.data.model.gameModels;

import androidx.room.ColumnInfo;
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
 * Model of the Line Bisection Test
 */
@Data
@Entity(tableName = "line_dissection_table")
public class LineBisectionGame {

    @PrimaryKey(autoGenerate = true)
    private int lineDissectionId;
    @ColumnInfo(name = "distance_in_mm")
    private float distanceToMiddleOfTheLine;
    private int milisec;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public LineBisectionGame(float distanceToMiddleOfTheLine, int milisec, int fkOperationId) {
        this.distanceToMiddleOfTheLine = distanceToMiddleOfTheLine;
        this.milisec = milisec;
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
     *
     * @param localDateTime formats generated datetime to: JJJJ-MM-DDT00:00:00.000
     */
    private String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return localDateTime.format(formatter);
    }

}
