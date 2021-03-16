package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.ReadDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.ReadGame;

/**
 * handles all data operations for the read model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class ReadRepository {
    private final ReadDao readDao;

    public ReadRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        readDao = craniowakeDatabase.readDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param readGame objekt to be saved in database
     */
    public void insert(ReadGame readGame) {
        new ReadRepository.AddReadGameAsyncTask(readDao).execute(readGame);
    }

    private static class AddReadGameAsyncTask extends AsyncTask<ReadGame, Void, Void> {
        private final ReadDao readDao;

        private AddReadGameAsyncTask(ReadDao readDao) {
            this.readDao = readDao;
        }

        @Override
        protected Void doInBackground(ReadGame... readGames) {
            readDao.addReadGame(readGames[0]);
            return null;
        }
    }
}
