package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.StroopGame;

/**
 * data access object which handles all Queries for the StroopGame model.
 */
@Dao
public abstract class StroopDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param stroopGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addStroopGame(StroopGame stroopGame);
}
