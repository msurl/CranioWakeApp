package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.FourSquareDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.FourSquareGame;

/**
 * handles all data operations for the fourSquare model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class FourSquareRepository {
    private final FourSquareDao fourSquareDao;

    public FourSquareRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        fourSquareDao = craniowakeDatabase.fourSquareDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param fourSquareGame objekt to be saved in database
     */
    public void insert(FourSquareGame fourSquareGame) {
        new FourSquareRepository.AddFourSquareGameAsyncTask(fourSquareDao).execute(fourSquareGame);
    }

    private static class AddFourSquareGameAsyncTask extends AsyncTask<FourSquareGame, Void, Void> {
        private final FourSquareDao fourSquareDao;

        private AddFourSquareGameAsyncTask(FourSquareDao fourSquareDao) {
            this.fourSquareDao = fourSquareDao;
        }

        @Override
        protected Void doInBackground(FourSquareGame... fourSquareGames) {
            fourSquareDao.addFourSquareGame(fourSquareGames[0]);
            return null;
        }
    }
}
