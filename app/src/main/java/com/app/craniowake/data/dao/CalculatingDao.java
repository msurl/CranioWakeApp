package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.CalculusGame;

/**
 * data access object which handles all Queries for the CalculusGame model.
 */
@Dao
public abstract class CalculatingDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param calculusGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addCalculatingGame(CalculusGame calculusGame);

}
