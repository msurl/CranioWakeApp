package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.LineBisectionGame;
import com.app.craniowake.data.repositories.LineBisectionRepository;
/**
 * stores and manages UI-related data of the LineBisectionActivity and is used as an abstraction Layer
 */
public class LineBisectionViewModel extends AndroidViewModel {
    private final LineBisectionRepository lineBisectionRepository;

    public LineBisectionViewModel(@NonNull Application application) {
        super(application);
        lineBisectionRepository = new LineBisectionRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     * @param lineBisectionGame is generated in LineBisectionActivity and send to be saved to db
     */
    public void addLineDissectionGame(LineBisectionGame lineBisectionGame) {
        lineBisectionRepository.insert(lineBisectionGame);
    }
}
