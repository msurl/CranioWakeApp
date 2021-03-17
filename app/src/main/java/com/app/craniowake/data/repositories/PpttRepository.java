package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.PpttDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.PpttGame;

/**
 * handles all data operations for the pptt model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class PpttRepository {
    private final PpttDao ppttDao;

    public PpttRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        ppttDao = craniowakeDatabase.ppttDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     *
     * @param ppttGame objekt to be saved in database
     */
    public void insert(PpttGame ppttGame) {
        new PpttRepository.AddPpttGameAsyncTask(ppttDao).execute(ppttGame);
    }

    private static class AddPpttGameAsyncTask extends AsyncTask<PpttGame, Void, Void> {
        private final PpttDao ppttDao;

        private AddPpttGameAsyncTask(PpttDao ppttDao) {
            this.ppttDao = ppttDao;
        }

        @Override
        protected Void doInBackground(PpttGame... ppttGames) {
            ppttDao.addPpttGame(ppttGames[0]);
            return null;
        }
    }
}
