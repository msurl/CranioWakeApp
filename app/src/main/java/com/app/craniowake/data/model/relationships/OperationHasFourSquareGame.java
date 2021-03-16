package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.FourSquareGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasFourSquareGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "fourSquareId"
    )
    public List<FourSquareGame> fourSquareGames;

    public OperationHasFourSquareGame(Operation operation, List<FourSquareGame> fourSquareGames) {
        this.operation = operation;
        this.fourSquareGames = fourSquareGames;
    }
}
