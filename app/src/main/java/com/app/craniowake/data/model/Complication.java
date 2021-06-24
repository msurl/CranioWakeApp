package com.app.craniowake.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Complication
 */
@Data
@Entity(tableName = "complication_table")
@NoArgsConstructor
public class Complication {

    @PrimaryKey(autoGenerate = true)
    private long complicationId;

    private LocalDateTime dateTime;
    private String text;
//    private String context;

    @ForeignKey
            (entity = Patient.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Deprecated
    @Ignore
    public Complication(long fkOperationId) {
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }

    @Ignore
    public Complication(long fkOperationId, String text) {
        this.fkOperationId = fkOperationId;
        this.text = text;
        this.dateTime = LocalDateTime.now();
    }
}
