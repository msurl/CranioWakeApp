package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.LineBisectionDao;
import com.app.craniowake.data.dao.NoteDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.Note;
import com.app.craniowake.data.model.gameModels.LineBisectionGame;

public class NoteRepository {
    private final NoteDao noteDao;

    public NoteRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        noteDao = craniowakeDatabase.noteDao();
    }

    public void insert(Note note) {
        new AddNoteAsyncTask(noteDao).execute(note);
    }

    private static class AddNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao noteDao;

        private AddNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.addNote(notes[0]);
            return null;
        }
    }
}
