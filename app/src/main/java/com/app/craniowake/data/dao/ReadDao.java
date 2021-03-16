package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.ReadGame;

/**
 * data access object which handles all Queries for the ReadGame model.
 */
@Dao
public abstract class ReadDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param readGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addReadGame(ReadGame readGame);
}
