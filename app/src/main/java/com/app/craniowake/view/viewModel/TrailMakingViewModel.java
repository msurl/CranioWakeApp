package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.TrailMakingGame;
import com.app.craniowake.data.repositories.TrailMakingRepository;
/**
 * stores and manages UI-related data of the TrailMakingActivity and is used as an abstraction Layer
 */
public class TrailMakingViewModel extends AndroidViewModel {
    private final TrailMakingRepository trailMakingRepository;

    public TrailMakingViewModel(@NonNull Application application) {
        super(application);
        trailMakingRepository = new TrailMakingRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     * @param trailMakingGame is generated in TrailMakingActivity and send to be saved to db
     */
    public void addTrailwayGame(TrailMakingGame trailMakingGame) {
        trailMakingRepository.insert(trailMakingGame);
    }
}
