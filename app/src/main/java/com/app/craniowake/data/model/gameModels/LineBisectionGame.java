package com.app.craniowake.data.model.gameModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Line Bisection Test
 */
@Data
@Entity(tableName = "line_bisection_test_table")
public class LineBisectionGame implements CurrentDateTimable {

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
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
