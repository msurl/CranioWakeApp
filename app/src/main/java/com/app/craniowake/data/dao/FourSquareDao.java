package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.FourSquareGame;

/**
 * data access object which handles all Queries for the FourSquareGame model.
 */
@Dao
public abstract class FourSquareDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param fourSquareGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addFourSquareGame(FourSquareGame fourSquareGame);

}
