package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.Complication;

/**
 * data access object which handles all Queries for the Complication model.
 */
@Dao
public abstract class ComplicationDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param complication object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addComplication(Complication complication);
}
