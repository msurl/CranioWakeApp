package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.TrailMakingGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasTrailwayGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "trailwayId"
    )
    public List<TrailMakingGame> trailMakingGames;

    public OperationHasTrailwayGame(Operation operation, List<TrailMakingGame> trailMakingGames) {
        this.operation = operation;
        this.trailMakingGames = trailMakingGames;
    }
}
