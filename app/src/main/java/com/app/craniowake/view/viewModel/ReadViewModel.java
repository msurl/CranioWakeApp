package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.ReadGame;
import com.app.craniowake.data.repositories.ReadRepository;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the ReadActivity and is used as an abstraction Layer
 */
public class ReadViewModel extends WithStimulationViewModel {
    private final ReadRepository readRepository;

    private final String[] textCollection;
    private final Random random = new Random();

    @Getter
    private MutableLiveData<ReadGame> currentGame;

    @Getter
    private int totalMistakes;

    @Setter
    private long operationId;


    public ReadViewModel(@NonNull Application application) {
        super(application);
        readRepository = new ReadRepository(application);

        totalMistakes = 0;

        currentGame = new MutableLiveData<>();

        textCollection = application.getResources().getStringArray(R.array.read_game_texts);
        randomiseText();
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param readGame is generated in ReadActivity and send to be saved to db
     */
    public void addReadGame(ReadGame readGame) {
        readRepository.insert(readGame);
    }

    public void randomiseText() {
        String text = textCollection[random.nextInt(textCollection.length)];

        currentGame.setValue(new ReadGame(text, operationId));
    }

    public void addMistake() {
        ReadGame game = currentGame.getValue();
        game.incrementMistakes();
        // necessary to call MutableLiveData.setValue() to dispatch new value to observers.
        currentGame.setValue(game);
    }

    public void subMistake() {
        ReadGame game = currentGame.getValue();
        game.decrementMistakes();
        // necessary to call MutableLiveData.setValue() to dispatch new value to observers.
        currentGame.setValue(game);
    }

    public void nextText() {
        ReadGame game = currentGame.getValue();
        game.setStimulation(getStimulationNumeric());
        game.setStimulationType(getStimulationType());
        addReadGame(game);
        totalMistakes += game.getMistakeCounter();
        randomiseText();
    }
}
