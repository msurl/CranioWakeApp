package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.TrailMakingGame;

/**
 * data access object which handles all Queries for the TrailMakingGame model.
 */
@Dao
public abstract class TrailMakingDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param trailMakingGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addTrailwayGame(TrailMakingGame trailMakingGame);
}
