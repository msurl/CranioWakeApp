package com.app.craniowake.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Complication
 */
@Data
@Entity(tableName = "complication_table")
public class Complication {

    @PrimaryKey(autoGenerate = true)
    private long complicationId;

    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Patient.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public Complication(long fkOperationId) {
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
