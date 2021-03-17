package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.ComplicationDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.Complication;

/**
 * handles all data operations for the complication model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class ComplicationRepository {
    private final ComplicationDao complicationDao;

    public ComplicationRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        complicationDao = craniowakeDatabase.complicationDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     *
     * @param complication objekt to be saved in database
     */
    public void insert(Complication complication) {
        new AddComplicationAsyncTask(complicationDao).execute(complication);
    }

    private static class AddComplicationAsyncTask extends AsyncTask<Complication, Void, Void> {
        private final ComplicationDao complicationDao;

        private AddComplicationAsyncTask(ComplicationDao complicationDao) {
            this.complicationDao = complicationDao;
        }

        @Override
        protected Void doInBackground(Complication... complications) {
            complicationDao.addComplication(complications[0]);
            return null;
        }
    }
}
