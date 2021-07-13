package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.db.ClinicalTestDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.stimulation.ClinicalTest;

public class ClinicalTestRepository {
    private ClinicalTestDao clinicalTestDao;

    public ClinicalTestRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        clinicalTestDao = craniowakeDatabase.clinicalTestDao();
    }

    public void insert(ClinicalTest clinicalTest) {
        new AddClinicalTestAsyncTask(clinicalTestDao).execute(clinicalTest);
    }

    private static class AddClinicalTestAsyncTask extends AsyncTask<ClinicalTest, Void, Void> {
        private final ClinicalTestDao clinicalTestDao;

        private AddClinicalTestAsyncTask(ClinicalTestDao clinicalTestDao) {
            this.clinicalTestDao = clinicalTestDao;
        }

        @Override
        protected Void doInBackground(ClinicalTest... clinicalTests) {
            clinicalTestDao.addClinicalTest(clinicalTests[0]);
            return null;
        }
    }
}
