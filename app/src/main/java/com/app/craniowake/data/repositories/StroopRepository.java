package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.StroopDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.StroopGame;

/**
 * handles all data operations for the stroop model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class StroopRepository {
    private final StroopDao stroopDao;

    public StroopRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        stroopDao = craniowakeDatabase.stroopDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param stroopGame objekt to be saved in database
     */
    public void insert(StroopGame stroopGame) {
        new StroopRepository.AddStroopGameAsyncTask(stroopDao).execute(stroopGame);
    }

    private static class AddStroopGameAsyncTask extends AsyncTask<StroopGame, Void, Void> {
        private final StroopDao stroopDao;

        private AddStroopGameAsyncTask(StroopDao stroopDao) {
            this.stroopDao = stroopDao;
        }

        @Override
        protected Void doInBackground(StroopGame... stroopGames) {
            stroopDao.addStroopGame(stroopGames[0]);
            return null;
        }
    }
}
