package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.StroopGame;
import com.app.craniowake.data.repositories.StroopRepository;
/**
 * stores and manages UI-related data of the StroopActivity and is used as an abstraction Layer
 */
public class StroopViewModel extends AndroidViewModel {
    private final StroopRepository stroopRepository;

    public StroopViewModel(@NonNull Application application) {
        super(application);
        stroopRepository = new StroopRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     * @param stroopGame is generated in StroopActivity and send to be saved to db
     */
    public void addStroopGame(StroopGame stroopGame) {
        stroopRepository.insert(stroopGame);
    }
}
