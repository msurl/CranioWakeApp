package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.Complication;
import com.app.craniowake.data.repositories.ComplicationRepository;

/**
 * stores and manages UI-related data of a Complication and is used as an abstraction Layer
 */
public class ComplicationViewModel extends AndroidViewModel {

    private final ComplicationRepository complicationRepository;

    public ComplicationViewModel(@NonNull Application application) {
        super(application);
        complicationRepository = new ComplicationRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param complication is generated in OperationActivity and send to be saved to db
     */
    public void addComplication(Complication complication) {
        complicationRepository.insert(complication);
    }
}
