package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.LineBisectionDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.LineBisectionGame;

/**
 * handles all data operations for the lineBisection model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class LineBisectionRepository {
    private final LineBisectionDao lineBisectionDao;

    public LineBisectionRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        lineBisectionDao = craniowakeDatabase.lineDissectionDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param lineBisectionGame objekt to be saved in database
     */
    public void insert(LineBisectionGame lineBisectionGame) {
        new LineBisectionRepository.AddLineDissectionGameAsyncTask(lineBisectionDao).execute(lineBisectionGame);
    }

    private static class AddLineDissectionGameAsyncTask extends AsyncTask<LineBisectionGame, Void, Void> {
        private final LineBisectionDao lineBisectionDao;

        private AddLineDissectionGameAsyncTask(LineBisectionDao lineBisectionDao) {
            this.lineBisectionDao = lineBisectionDao;
        }

        @Override
        protected Void doInBackground(LineBisectionGame... lineBisectionGames) {
            lineBisectionDao.addLineDissectionGame(lineBisectionGames[0]);
            return null;
        }
    }
}
