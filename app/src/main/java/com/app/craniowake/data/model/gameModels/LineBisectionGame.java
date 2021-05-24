package com.app.craniowake.data.model.gameModels;

import androidx.room.ColumnInfo;
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
 * Model of the Line Bisection Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "line_bisection_test_table")
public class LineBisectionGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long lineDissectionId;
    @ColumnInfo(name = "distance_in_mm")
    private float distanceToMiddleOfTheLine;
    private int milisec;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public LineBisectionGame(float distanceToMiddleOfTheLine, int milisec, long fkOperationId) {
        super();
        this.distanceToMiddleOfTheLine = distanceToMiddleOfTheLine;
        this.milisec = milisec;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public LineBisectionGame(float distanceToMiddleOfTheLine, int milisec, double stimulation, long fkOperationId) {
        super(stimulation);
        this.distanceToMiddleOfTheLine = distanceToMiddleOfTheLine;
        this.milisec = milisec;
        this.fkOperationId = fkOperationId;
    }
}
