package com.app.craniowake.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Complication
 */
@Data
@Entity(tableName = "complication_table")
public class Complication implements CurrentDateTimable{

    @PrimaryKey(autoGenerate = true)
    private int complicationId;

    private String dateTime;
    @ForeignKey
            (entity = Patient.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public Complication(int fkOperationId) {
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
