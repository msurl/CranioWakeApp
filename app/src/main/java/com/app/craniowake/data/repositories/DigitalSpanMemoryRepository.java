package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.DigitalSpanMemoryDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;

/**
 * handles all data operations for the digitalSpanMemory model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class DigitalSpanMemoryRepository {
    private final DigitalSpanMemoryDao digitalSpanMemoryDao;

    public DigitalSpanMemoryRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        digitalSpanMemoryDao = craniowakeDatabase.digitalSpanMemoryDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     *
     * @param digitalSpanMemoryGame objekt to be saved in database
     */
    public void insert(DigitalSpanMemoryGame digitalSpanMemoryGame) {
        new DigitalSpanMemoryRepository.AddDigitalSpanMemoryGameAsyncTask(digitalSpanMemoryDao).execute(digitalSpanMemoryGame);
    }

    private static class AddDigitalSpanMemoryGameAsyncTask extends AsyncTask<DigitalSpanMemoryGame, Void, Void> {
        private final DigitalSpanMemoryDao digitalSpanMemoryDao;

        private AddDigitalSpanMemoryGameAsyncTask(DigitalSpanMemoryDao digitalSpanMemoryDao) {
            this.digitalSpanMemoryDao = digitalSpanMemoryDao;
        }

        @Override
        protected Void doInBackground(DigitalSpanMemoryGame... digitalSpanMemoryGames) {
            digitalSpanMemoryDao.addDigitalSpanMemoryGame(digitalSpanMemoryGames[0]);
            return null;
        }
    }
}
