package com.app.craniowake.view.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.data.model.Complication;
import com.app.craniowake.data.repositories.ComplicationRepository;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of a Complication and is used as an abstraction Layer
 */
public class ComplicationViewModel extends AndroidViewModel {

    private final ComplicationRepository complicationRepository;

    @Getter
    private MutableLiveData<String> text;

    @Setter
    private Long operationId;

    public ComplicationViewModel(@NonNull Application application) {
        super(application);
        complicationRepository = new ComplicationRepository(application);

        text = new MutableLiveData<>("");
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param complication is generated in OperationActivity and send to be saved to db
     */
    public void addComplication(Complication complication) {
        complicationRepository.insert(complication);
    }

    public void addComplication(Long operationId) {
        addComplication(new Complication(operationId, text.getValue()));
    }

    public void addComplication() {
        addComplication(new Complication(this.operationId, text.getValue()));
    }
}
