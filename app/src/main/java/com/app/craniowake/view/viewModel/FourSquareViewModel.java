package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.FourSquareGame;
import com.app.craniowake.data.repositories.FourSquareRepository;

/**
 * stores and manages UI-related data of the FourSquareActivity and is used as an abstraction Layer
 */
public class FourSquareViewModel extends AndroidViewModel {
    private final FourSquareRepository fourSquareRepository;


    public FourSquareViewModel(@NonNull Application application) {
        super(application);
        fourSquareRepository = new FourSquareRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param fourSquareGame is generated in FourSquareActivity and send to be saved to db
     */
    public void addFourSquareGame(FourSquareGame fourSquareGame) {
        fourSquareRepository.insert(fourSquareGame);
    }
}
