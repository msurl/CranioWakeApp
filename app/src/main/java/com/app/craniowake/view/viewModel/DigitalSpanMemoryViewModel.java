package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;
import com.app.craniowake.data.repositories.DigitalSpanMemoryRepository;

/**
 * stores and manages UI-related data of the DigitalSpanMemoryActivity and is used as an abstraction Layer
 */
public class DigitalSpanMemoryViewModel extends AndroidViewModel {

    private final DigitalSpanMemoryRepository digitalSpanMemoryRepository;

    public DigitalSpanMemoryViewModel(@NonNull Application application) {
        super(application);
        digitalSpanMemoryRepository = new DigitalSpanMemoryRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param digitalSpanMemoryGame is generated in DigitalSpanMemoryActivity and send to be saved to db
     */
    public void addDigitalSpanMemoryGame(DigitalSpanMemoryGame digitalSpanMemoryGame) {
        digitalSpanMemoryRepository.insert(digitalSpanMemoryGame);
    }
}
