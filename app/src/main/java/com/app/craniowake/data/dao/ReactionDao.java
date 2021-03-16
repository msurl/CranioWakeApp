package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.ReactionGame;

/**
 * data access object which handles all Queries for the ReactionGame model.
 */
@Dao
public abstract class ReactionDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param reactionGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addReactionGame(ReactionGame reactionGame);
}
