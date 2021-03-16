package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.ReadGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasReadGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "readId"
    )
    public List<ReadGame> readGames;

    public OperationHasReadGame(Operation operation, List<ReadGame> readGames) {
        this.operation = operation;
        this.readGames = readGames;
    }
}
