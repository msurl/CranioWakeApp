package com.app.craniowake.view.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.repositories.OperationRepository;

import java.time.LocalDateTime;

/**
 * stores and manages UI-related data of the OperationActivity and is used as an abstraction Layer
 */
public class OperationViewModel extends AndroidViewModel {

    private final OperationRepository operationRepository;

    public OperationViewModel(@NonNull Application application) {
        super(application);
        operationRepository = new OperationRepository(application);
    }

    /**
     * send current Operation as a LiveData object to Activity
     *
     * @param date identifier of which Operation to retrieve
     */
    public LiveData<Operation> getOperationByDate(LocalDateTime date) {
        return operationRepository.getOperationByDate(date);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param operation is generated in MainActivity and send to be saved to db
     */
    public AsyncTask<Operation, Void, Long> addOperation(Operation operation) {
        return operationRepository.insert(operation);
    }

//    public void addOperation(Operation operation, Consumer<Long> processIdAfterExecution) {
//        operationRepository.insert(operation);
//    }
}
