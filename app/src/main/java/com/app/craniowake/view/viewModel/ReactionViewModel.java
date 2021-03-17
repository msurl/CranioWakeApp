package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.ReactionGame;
import com.app.craniowake.data.repositories.ReactionRepository;

/**
 * stores and manages UI-related data of the ReactionActivity and is used as an abstraction Layer
 */
public class ReactionViewModel extends AndroidViewModel {
    private final ReactionRepository reactionRepository;

    public ReactionViewModel(@NonNull Application application) {
        super(application);
        reactionRepository = new ReactionRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param reactionGame is generated in ReactionActivity and send to be saved to db
     */
    public void addReactionGame(ReactionGame reactionGame) {
        reactionRepository.insert(reactionGame);
    }
}
