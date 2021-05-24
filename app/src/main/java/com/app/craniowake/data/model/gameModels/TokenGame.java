package com.app.craniowake.data.model.gameModels;

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
 * Model of the Token Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "token_test_table")
public class TokenGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long tokenId;
    private String token;
    private boolean answer;
    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public TokenGame(String token, boolean answer, long fkOperationId) {
        super();
        this.token = token;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }

    @Ignore
    public TokenGame(String token, boolean answer, double stimulation, long fkOperationId) {
        super(stimulation);
        this.token = token;
        this.answer = answer;
        this.fkOperationId = fkOperationId;
    }
}
