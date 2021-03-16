package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.PpttGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasPpttGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "ppttId"
    )
    public List<PpttGame> ppttGames;

    public OperationHasPpttGame(Operation operation, List<PpttGame> ppttGames) {
        this.operation = operation;
        this.ppttGames = ppttGames;
    }
}
