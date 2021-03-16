package com.app.craniowake.data.model.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.gameModels.PictureGame;

import java.util.List;

import lombok.Data;

/*** EXCLUSIVE CLASS TO MANAGE ONE TO MANY IN ROOM  ***/
@Data
public class OperationHasPictureGame {
    @Embedded
    public Operation operation;
    @Relation(
            parentColumn = "operationId",
            entityColumn = "pictureId"
    )
    public List<PictureGame> pictureGames;

    public OperationHasPictureGame(Operation operation, List<PictureGame> pictureGames) {
        this.operation = operation;
        this.pictureGames = pictureGames;
    }
}
