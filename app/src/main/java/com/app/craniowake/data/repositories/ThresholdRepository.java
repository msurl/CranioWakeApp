package com.app.craniowake.data.repositories;


import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.ThresholdDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.stimulation.ThresholdTest;

/**
 * handles all data operations for the {@link ThresholdTest} model and {@link com.app.craniowake.data.dao.ThresholdDao}.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class ThresholdRepository {
    private final ThresholdDao thresholdDao;

    public ThresholdRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        thresholdDao = craniowakeDatabase.thresholdDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     *
     * @param thresholdTest object to be saved in database
     */
    public void insert(ThresholdTest thresholdTest) {
        new ThresholdRepository.AddThresholdTestAsyncTask(thresholdDao).execute(thresholdTest);
    }

    private static class AddThresholdTestAsyncTask extends AsyncTask<ThresholdTest, Void, Void> {
        private final ThresholdDao thresholdDao;

        private AddThresholdTestAsyncTask(ThresholdDao thresholdDao) {
            this.thresholdDao = thresholdDao;
        }

        @Override
        protected Void doInBackground(ThresholdTest... thresholdTests) {
            ThresholdTest test = thresholdTests[0];
            thresholdDao.addThresholdTest(thresholdTests[0]);

            final Long id = thresholdDao.addThresholdTest(test);
            test.setThresholdId(id);

            test.getStimulations().forEach(stimulation -> {
                stimulation.setThresholdId(id);
                thresholdDao.addStimulation(stimulation);
            });
            return null;
        }
    }
}
