package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.CalculusGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasCalculatingGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "calcGameId"
    )
    public List<CalculusGame> calculusGames;

    public OperationHasCalculatingGame(Operation operation, List<CalculusGame> calculusGames) {
        this.operation = operation;
        this.calculusGames = calculusGames;
    }
}
