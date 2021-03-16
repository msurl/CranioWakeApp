package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.TrailMakingDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.TrailMakingGame;

/**
 * handles all data operations for the trailMaking model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class TrailMakingRepository {
    private final TrailMakingDao trailMakingDao;

    public TrailMakingRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        trailMakingDao = craniowakeDatabase.trailwayDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param trailMakingGame objekt to be saved in database
     */
    public void insert(TrailMakingGame trailMakingGame) {
        new TrailMakingRepository.AddTrailwayGameAsyncTask(trailMakingDao).execute(trailMakingGame);
    }

    private static class AddTrailwayGameAsyncTask extends AsyncTask<TrailMakingGame, Void, Void> {
        private final TrailMakingDao trailMakingDao;

        private AddTrailwayGameAsyncTask(TrailMakingDao trailMakingDao) {
            this.trailMakingDao = trailMakingDao;
        }

        @Override
        protected Void doInBackground(TrailMakingGame... trailMakingGames) {
            trailMakingDao.addTrailwayGame(trailMakingGames[0]);
            return null;
        }
    }
}
