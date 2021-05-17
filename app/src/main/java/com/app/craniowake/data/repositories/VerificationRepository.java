package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.app.craniowake.data.dao.VerificationDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.stimulation.VerificationTest;

/**
 * handles all data operations for the {@link VerificationTest} model and {@link com.app.craniowake.data.dao.VerificationDao}.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class VerificationRepository {
    private final VerificationDao verificationDao;

    public VerificationRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        verificationDao = craniowakeDatabase.verificationDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     *
     * @param verificationTest object to be saved in database
     */
    public void insert(VerificationTest verificationTest) {
        new AddVerificationTestAsyncTask(verificationDao).execute(verificationTest);
    }

    public LiveData<VerificationTest> getVerificationTestByOperationId(int id) {
        return verificationDao.getByOperationId(id);
    }

    private static class AddVerificationTestAsyncTask extends AsyncTask<VerificationTest, Void, Void> {
        private final VerificationDao verificationDao;

        private AddVerificationTestAsyncTask(VerificationDao verificationDao) {
            this.verificationDao = verificationDao;
        }

        @Override
        protected Void doInBackground(VerificationTest... verificationTests) {
            VerificationTest test = verificationTests[0];
            final Long id = verificationDao.addVerificationTest(test);
            test.setVerificationId(id);

            test.getStimulations().forEach(stimulation -> {
                stimulation.setVerificationId(id);
                verificationDao.addStimulation(stimulation);
            });
            return null;
        }
    }
}
