package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.LineBisectionGame;

/**
 * data access object which handles all Queries for the LineBisectionGame model.
 */
@Dao
public abstract class LineBisectionDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param lineBisectionGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addLineDissectionGame(LineBisectionGame lineBisectionGame);
}
