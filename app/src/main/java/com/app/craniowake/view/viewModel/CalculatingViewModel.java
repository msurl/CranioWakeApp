package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.CalculusGame;
import com.app.craniowake.data.repositories.CalculatingRepository;

/**
 * stores and manages UI-related data of the CalculusActivity and is used as an abstraction Layer
 */
public class CalculatingViewModel extends AndroidViewModel {

    private final CalculatingRepository calculatingRepository;

    public CalculatingViewModel(@NonNull Application application) {
        super(application);
        calculatingRepository = new CalculatingRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param calculusGame is generated in CalculusActivity and send to be saved to db
     */
    public void addCalculatingGame(CalculusGame calculusGame) {
        calculatingRepository.insert(calculusGame);
    }
}
