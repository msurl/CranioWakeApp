package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.CalculatingDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.CalculusGame;

/**
 * handles all data operations for the calculating model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class CalculatingRepository {
    private final CalculatingDao calculatingDao;

    public CalculatingRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        calculatingDao = craniowakeDatabase.calculatingDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param calculusGame objekt to be saved in database
     */
    public void insert(CalculusGame calculusGame) {
        new CalculatingRepository.AddCalculatingGameAsyncTask(calculatingDao).execute(calculusGame);
    }

    private static class AddCalculatingGameAsyncTask extends AsyncTask<CalculusGame, Void, Void> {
        private final CalculatingDao calculatingDao;

        private AddCalculatingGameAsyncTask(CalculatingDao calculatingDao) {
            this.calculatingDao = calculatingDao;
        }

        @Override
        protected Void doInBackground(CalculusGame... calculusGames) {
            calculatingDao.addCalculatingGame(calculusGames[0]);
            return null;
        }
    }
}
