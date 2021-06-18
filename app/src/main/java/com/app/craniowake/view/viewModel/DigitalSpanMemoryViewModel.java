package com.app.craniowake.view.viewModel;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;
import com.app.craniowake.data.repositories.DigitalSpanMemoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the DigitalSpanMemoryActivity and is used as an abstraction Layer
 */
public class DigitalSpanMemoryViewModel extends WithStimulationViewModel {

    private final DigitalSpanMemoryRepository digitalSpanMemoryRepository;

    @Getter
    @Setter
    volatile boolean shutdown = false;

    private final String start;
    @Getter
    private int correctAnswers = 0;
    @Getter
    private int wrongAnswers = 0;

    private final int[] currentNumbers = new int[3];
    private final MutableLiveData<Integer> currentIndex;
    @Getter
    private final LiveData<String> currentNumber;

    @Getter
    private boolean started;

    private static final int bound = 10;

    private final Random random = new Random();

    @Setter
    private long operationId;


    public DigitalSpanMemoryViewModel(@NonNull Application application) {
        super(application);
        digitalSpanMemoryRepository = new DigitalSpanMemoryRepository(application);

        start = application.getString(R.string.start);

        shutdown = false;
        started = false;

        correctAnswers = 0;
        wrongAnswers = 0;

//        started = new MutableLiveData<>(false);

        createRandomNumbers();

        // currentIndex holds indices from 0 to currentNumbers.length*2 +1
        // if currentIndex is the twofold of a integer then we want to display one of the currentNumbers
        // if it is not dividable by two we want to show a empty string to give the patient time to prepare for the next number
        currentIndex = new MutableLiveData<>(0);
        currentNumber = Transformations.map(currentIndex, i -> {
            if (i == 0)
                return start;
            else if(i % 2 == 0)
                return currentNumbers[i/2-1]+"";
            else
                return "";
        });
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param digitalSpanMemoryGame is generated in DigitalSpanMemoryActivity and send to be saved to db
     */
    public void addDigitalSpanMemoryGame(DigitalSpanMemoryGame digitalSpanMemoryGame) {
        digitalSpanMemoryRepository.insert(digitalSpanMemoryGame);
    }

    public void addDigitalSpanMemoryGame(boolean answer) {
        addDigitalSpanMemoryGame(answer, this.operationId);
    }

    public void addDigitalSpanMemoryGame(boolean answer, Long operationId) {
        digitalSpanMemoryRepository.insert(new DigitalSpanMemoryGame(answer, getStimulation().getValue(), operationId));
    }

    public void answer(boolean correct) {
        answer(correct, operationId);
    }

    public void answer(boolean correct, Long operationId) {
        correctAnswers += correct ? 1:0;
        wrongAnswers += correct ? 0:1;

        shutdown = true;
        started = false;
        addDigitalSpanMemoryGame(correct, operationId);
        createRandomNumbers();
        resetIndex();
    }

    private void createRandomNumbers() {
        List<Integer> potentialNumbers = new ArrayList<>();
        for(int i = 1; i <= bound; i++) {
            potentialNumbers.add(i);
        }

        for (int i = 0; i < currentNumbers.length; i++) {
            int randomIndex = random.nextInt(potentialNumbers.size());
            currentNumbers[i] = potentialNumbers.get(randomIndex);

            potentialNumbers.remove(randomIndex);
        }
    }

    public void resetIndex() {
        currentIndex.setValue(0);
    }

    public void incrementIndex() {
        int index = currentIndex.getValue();
        currentIndex.setValue(index+1);
    }

    public void start() {
        started = true;
    }
}
