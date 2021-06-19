package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.data.model.gameModels.CalculusGame;
import com.app.craniowake.data.repositories.CalculatingRepository;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the CalculusActivity and is used as an abstraction Layer
 */
public class CalculatingViewModel extends WithStimulationViewModel {

    private final CalculatingRepository calculatingRepository;

    @Getter
    private MutableLiveData<CalculusGame> game;

    @Getter
    private int correct;
    @Getter
    private int wrong;

    private static int numbScale = 21;

    @Setter
    private Long operationId;

    public CalculatingViewModel(@NonNull Application application) {
        super(application);
        calculatingRepository = new CalculatingRepository(application);

        initAnswers();

        this.game = new MutableLiveData<>(new CalculusGame(numbScale));
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param calculusGame is generated in CalculusActivity and send to be saved to db
     */
    public void addCalculatingGame(CalculusGame calculusGame) {
        calculatingRepository.insert(calculusGame);
    }

    public void addWrongAnswer() {
        wrong++;
    }

    public void addCorrectAnswer() {
        correct++;
    }

    public void initAnswers() {
        wrong = 0;
        correct = 0;
    }

    public void answer(Long operationId) {
        CalculusGame currentGame = game.getValue();
        currentGame.setFkOperationId(operationId);

        if(isStimulated())
        {
            currentGame.setStimulation(getStimulationNumeric());
            resetStimulation();
        }

        if (currentGame.isCorrect()){
            addCorrectAnswer();
        }
        else {
            addWrongAnswer();
        }

        addCalculatingGame(currentGame);
        game.setValue(new CalculusGame(numbScale));
    }

    public void answer() {
        answer(this.operationId);
    }
}
