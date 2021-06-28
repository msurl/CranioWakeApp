package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.Note;

@Dao
public abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long addNote(Note note);
}
