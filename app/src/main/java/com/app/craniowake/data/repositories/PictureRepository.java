package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.PictureDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.PictureGame;

/**
 * handles all data operations for the picture model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class PictureRepository {
    private final PictureDao pictureDao;

    public PictureRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        pictureDao = craniowakeDatabase.pictureDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param pictureGame objekt to be saved in database
     */
    public void insert(PictureGame pictureGame) {
        new PictureRepository.AddPictureGameAsyncTask(pictureDao).execute(pictureGame);
    }

    private static class AddPictureGameAsyncTask extends AsyncTask<PictureGame, Void, Void> {
        private final PictureDao pictureDao;

        private AddPictureGameAsyncTask(PictureDao pictureDao) {
            this.pictureDao = pictureDao;
        }

        @Override
        protected Void doInBackground(PictureGame... pictureGames) {
            pictureDao.addPictureGame(pictureGames[0]);
            return null;
        }
    }
}
