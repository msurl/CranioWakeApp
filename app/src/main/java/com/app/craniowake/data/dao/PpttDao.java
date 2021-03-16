package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.PpttGame;

/**
 * data access object which handles all Queries for the PpttGame model.
 */
@Dao
public abstract class PpttDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param ppttGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addPpttGame(PpttGame ppttGame);
}
