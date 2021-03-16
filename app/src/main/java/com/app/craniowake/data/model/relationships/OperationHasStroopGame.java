package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.StroopGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasStroopGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "stroopId"
    )
    public List<StroopGame> stroopGames;

    public OperationHasStroopGame(Operation operation, List<StroopGame> stroopGames) {
        this.operation = operation;
        this.stroopGames = stroopGames;
    }
}
