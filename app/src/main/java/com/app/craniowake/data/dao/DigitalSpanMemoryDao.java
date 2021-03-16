package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;

/**
 * data access object which handles all Queries for the DigitalSpanMemoryGame model.
 */
@Dao
public abstract class DigitalSpanMemoryDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param digitalSpanMemoryGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addDigitalSpanMemoryGame(DigitalSpanMemoryGame digitalSpanMemoryGame);

}
