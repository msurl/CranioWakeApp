package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.LineBisectionGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasLineBisectionGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "lineDissectionId"
    )
    public List<LineBisectionGame> lineBisectionGames;

    public OperationHasLineBisectionGame(Operation operation, List<LineBisectionGame> lineBisectionGames) {
        this.operation = operation;
        this.lineBisectionGames = lineBisectionGames;
    }
}
