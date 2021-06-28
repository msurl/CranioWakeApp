package com.app.craniowake.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

@Data
@Entity(tableName = "note_table")
@NoArgsConstructor
public class Note extends ModelWithCreationTimeStamp{

    @PrimaryKey(autoGenerate = true)
    private long noteId;

    private String text;
    private String activityName;

    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public Note(String text, String activityName, long fkOperationId) {
        this.text = text;
        this.activityName = activityName;
        this.fkOperationId = fkOperationId;
    }
}
