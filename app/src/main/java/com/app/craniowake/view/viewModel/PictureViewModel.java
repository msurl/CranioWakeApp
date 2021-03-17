package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.PictureGame;
import com.app.craniowake.data.repositories.PictureRepository;

/**
 * stores and manages UI-related data of the PictureActivity and is used as an abstraction Layer
 */
public class PictureViewModel extends AndroidViewModel {
    private final PictureRepository pictureRepository;

    public PictureViewModel(@NonNull Application application) {
        super(application);
        pictureRepository = new PictureRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param pictureGame is generated in PictureActivity and send to be saved to db
     */
    public void addPictureGame(PictureGame pictureGame) {
        pictureRepository.insert(pictureGame);
    }
}
