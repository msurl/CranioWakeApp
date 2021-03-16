package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.TokenGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasTokenGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "tokenId"
    )
    public List<TokenGame> tokenGames;

    public OperationHasTokenGame(Operation operation, List<TokenGame> tokenGames) {
        this.operation = operation;
        this.tokenGames = tokenGames;
    }
}
