package com.app.craniowake.data.model.gameModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Line Bisection Test
 */
@Data
@Entity(tableName = "line_bisection_test_table")
public class LineBisectionGame {

    @PrimaryKey(autoGenerate = true)
    private long lineDissectionId;
    @ColumnInfo(name = "distance_in_mm")
    private float distanceToMiddleOfTheLine;
    private int milisec;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public LineBisectionGame(float distanceToMiddleOfTheLine, int milisec, long fkOperationId) {
        this.distanceToMiddleOfTheLine = distanceToMiddleOfTheLine;
        this.milisec = milisec;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
