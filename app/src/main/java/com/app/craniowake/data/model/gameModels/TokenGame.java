package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Token Test
 */
@Data
@Entity(tableName = "token_test_table")
public class TokenGame {

    @PrimaryKey(autoGenerate = true)
    private long tokenId;
    private String token;
    private boolean answer;
    private LocalDateTime dateTime;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    public TokenGame(String token, boolean answer, long fkOperationId) {
        this.token = token;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
        this.dateTime = LocalDateTime.now();
    }
}
