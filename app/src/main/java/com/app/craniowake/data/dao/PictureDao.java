package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.gameModels.PictureGame;

/**
 * data access object which handles all Queries for the PictureGame model.
 */
@Dao
public abstract class PictureDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param pictureGame object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addPictureGame(PictureGame pictureGame);
}
