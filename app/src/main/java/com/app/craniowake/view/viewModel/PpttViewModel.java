package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.PpttGame;
import com.app.craniowake.data.repositories.PpttRepository;

/**
 * stores and manages UI-related data of the PpttActivity and is used as an abstraction Layer
 */
public class PpttViewModel extends AndroidViewModel {
    private final PpttRepository ppttRepository;

    public PpttViewModel(@NonNull Application application) {
        super(application);
        ppttRepository = new PpttRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param ppttGame is generated in PpttActivity and send to be saved to db
     */
    public void addPpttGame(PpttGame ppttGame) {
        ppttRepository.insert(ppttGame);
    }
}
