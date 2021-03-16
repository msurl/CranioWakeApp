package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasDigitalSpanMemoryGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "digitalSpanMemoryId"
    )
    public List<DigitalSpanMemoryGame> digitalSpanMemoryGames;

    public OperationHasDigitalSpanMemoryGame(Operation operation, List<DigitalSpanMemoryGame> digitalSpanMemoryGames) {
        this.operation = operation;
        this.digitalSpanMemoryGames = digitalSpanMemoryGames;
    }
}
