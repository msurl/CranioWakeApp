package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.ReadGame;
import com.app.craniowake.data.repositories.ReadRepository;

/**
 * stores and manages UI-related data of the ReadActivity and is used as an abstraction Layer
 */
public class ReadViewModel extends AndroidViewModel {
    private final ReadRepository readRepository;

    public ReadViewModel(@NonNull Application application) {
        super(application);
        readRepository = new ReadRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param readGame is generated in ReadActivity and send to be saved to db
     */
    public void addReadGame(ReadGame readGame) {
        readRepository.insert(readGame);
    }
}
