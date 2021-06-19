package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.Game;
import com.app.craniowake.data.model.gameModels.TokenGame;
import com.app.craniowake.data.repositories.TokenRepository;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the TokenActivity and is used as an abstraction Layer
 */
public class TokenViewModel extends WithStimulationViewModel {
    private final String[] allToken;
    private final Random random = new Random();

    private final TokenRepository tokenRepository;

    @Getter
    private MutableLiveData<String> currentToken;
    @Getter
    private int correctAnswers;
    @Getter
    private int wrongAnswers;

    @Setter
    private long currentOperationId;

    public TokenViewModel(@NonNull Application application) {
        super(application);
        tokenRepository = new TokenRepository(application);

        allToken = getApplication().getResources().getStringArray(R.array.tokenView);

        currentToken = new MutableLiveData<>();
        randomiseToken();

        correctAnswers = 0;
        wrongAnswers = 0;
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param tokenGame is generated in TokenActivity and send to be saved to db
     */
    public void addTokenGame(TokenGame tokenGame) {
        tokenRepository.insert(tokenGame);
    }

    public void addTokenGame(String correctToken, String selectedToken, boolean correct, long operationId) {
        // TODO: Nicht vergessen, dies hier anzupassen, nachdem der Typ auswählbar ist!
        this.addTokenGame(new TokenGame(correctToken, selectedToken, correct, getStimulationNumeric(), getStimulationType(), operationId));
    }

    public void answer(String selectedToken) {
        String correctToken = currentToken.getValue();
        boolean correctAnswer = correctToken.equals(selectedToken);

        this.correctAnswers += correctAnswer ? 1 : 0;
        this.wrongAnswers += correctAnswer ? 0 : 1;

        this.addTokenGame(correctToken, selectedToken, correctAnswer, currentOperationId);

        randomiseToken();
    }


    private void randomiseToken() {
        String randomToken = allToken[random.nextInt(allToken.length)];
        currentToken.setValue(randomToken);
    }
}
