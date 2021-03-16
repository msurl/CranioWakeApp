package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.craniowake.data.model.gameModels.TokenGame;
import com.app.craniowake.data.repositories.TokenRepository;
/**
 * stores and manages UI-related data of the TokenActivity and is used as an abstraction Layer
 */
public class TokenViewModel extends AndroidViewModel {
    private final TokenRepository tokenRepository;

    public TokenViewModel(@NonNull Application application) {
        super(application);
        tokenRepository = new TokenRepository(application);
    }

    /**
     * passes the object to be saved in database to underlying repository
     * @param tokenGame is generated in TokenActivity and send to be saved to db
     */
    public void addTokenGame(TokenGame tokenGame) {
        tokenRepository.insert(tokenGame);
    }
}
