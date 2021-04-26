package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.CurrentDateTimable;
import com.app.craniowake.data.model.Operation;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Token Test
 */
@Data
@Entity(tableName = "token_test_table")
public class TokenGame implements CurrentDateTimable {

    @PrimaryKey(autoGenerate = true)
    private int tokenId;
    private String token;
    private boolean answer;
    private String dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private int fkOperationId;

    public TokenGame(String token, boolean answer, int fkOperationId) {
        this.token = token;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        applyCurrentDateTimeAsStringTo(this.dateTime);
    }
}
