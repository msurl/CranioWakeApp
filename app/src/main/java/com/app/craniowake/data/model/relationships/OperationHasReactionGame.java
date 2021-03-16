package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.ReactionGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasReactionGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "reactionId"
    )
    public List<ReactionGame> reactionGames;

    public OperationHasReactionGame(Operation operation, List<ReactionGame> reactionGames) {
        this.operation = operation;
        this.reactionGames = reactionGames;
    }
}
