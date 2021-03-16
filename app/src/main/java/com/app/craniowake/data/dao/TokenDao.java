package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.TokenGame;

/**
 * data access object which handles all Queries for the TokenGame model.
 */
@Dao
public abstract class TokenDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param tokenGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addTokenGame(TokenGame tokenGame);
}
