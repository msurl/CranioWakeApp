package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.core.util.Consumer;
import androidx.lifecycle.LiveData;

import com.app.craniowake.data.dao.OperationDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

/**
 * handles all data operations for the operation model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class OperationRepository {
    private final OperationDao operationDao;

    public OperationRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        operationDao = craniowakeDatabase.operationDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     *
     * @param newOperation objekt to be saved in database
     */
    public AsyncTask<Operation, Void, Long> insert(Operation newOperation) {
        return new AddOperationAsyncTask(operationDao).execute(newOperation);
    }

//    public void insert(Operation newOperation) {
//        return new AddOperationAsyncTask(operationDao).execute(newOperation);
//    }

    /**
     * returns LiveData of the given operation by date
     *
     * @param date is used as an identifier for operations
     */
    public LiveData<Operation> getOperationByDate(LocalDateTime date) {
        return operationDao.getOperationByDate(date);
    }

    private static class AddOperationAsyncTask extends AsyncTask<Operation, Void, Long> {
        private final OperationDao operationDao;

        private AddOperationAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }

        @Override
        protected Long doInBackground(Operation... operations) {
            long insertedId = operationDao.addOperation(operations[0]);
            operations[0].setOperationId((int) insertedId);
            return insertedId;
        }
    }

}
