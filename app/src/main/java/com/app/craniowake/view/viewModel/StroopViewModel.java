package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.StroopGame;
import com.app.craniowake.data.repositories.StroopRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the StroopActivity and is used as an abstraction Layer
 */
public class StroopViewModel extends WithStimulationViewModel {
    private final StroopRepository stroopRepository;

    @Getter
    private MutableLiveData<String> colorTextLiveData;
    private MutableLiveData<Integer> inkIndexLiveData;
    @Getter
    private LiveData<Integer> inkColorCodeLiveData;


    private final String[] stroopColors;
    private final int[] rainbow;

    @Getter
    private int correctAnswers;
    @Getter
    private int wrongAnswers;

    private final Random random = new Random();

    // evtl. auch diese Variable (wie auch in den anderen ViewModels) entfernen, den onClickListener in die Activity schieben und dort die ID übergeben.
    @Setter
    private long  currentOperationId;

    // evtl. hier rausnehmen und den OnClickListener eine Funktion aus der Activity callen lassen
    private Collection<Consumer<Boolean>> answerConsumers;

    public StroopViewModel(@NonNull Application application) {
        super(application);
        stroopRepository = new StroopRepository(application);

        stroopColors  = getApplication().getResources().getStringArray(R.array.colorNames);
        rainbow = getApplication().getResources().getIntArray(R.array.rainbow);

        colorTextLiveData = new MutableLiveData<>();
        inkIndexLiveData = new MutableLiveData<>();
        inkColorCodeLiveData = Transformations.map(inkIndexLiveData, index -> {
            return rainbow[index];
        });

        createRandomGame();

        correctAnswers = 0;
        wrongAnswers = 0;

        answerConsumers = new HashSet<>();
    }

    public void createRandomGame(){
        String colorText = stroopColors[random.nextInt(stroopColors.length)];
        int inkIndex = random.nextInt(rainbow.length);

        colorTextLiveData.setValue(colorText);
        inkIndexLiveData.setValue(inkIndex);
    }

    public void addAnswerConsumer(Consumer<Boolean> consumer) {
        answerConsumers.add(consumer);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param stroopGame is generated in StroopActivity and send to be saved to db
     */
    public void addStroopGame(StroopGame stroopGame) {
        stroopRepository.insert(stroopGame);
    }

    public void addStroopGame(String ink, String colorText, boolean correct, long fkOperationId) {
        addStroopGame(new StroopGame(ink, colorText, correct, getStimulationNumeric(),fkOperationId));
    }

    public void addStroopGame(boolean answer, long fkOperationId) {
        String inkText = stroopColors[inkIndexLiveData.getValue()];
        String colorText = colorTextLiveData.getValue();
        addStroopGame(inkText, colorText, answer, fkOperationId);
    }

    public void addStroopGame(boolean correct) {
        String inkText = stroopColors[inkIndexLiveData.getValue()];
        String colorText = colorTextLiveData.getValue();
        addStroopGame(inkText, colorText, correct, currentOperationId);
    }

    public void answer(boolean correct) {
        correctAnswers += correct ? 1:0;
        wrongAnswers += correct ? 0:1;

        answerConsumers.forEach(consumer -> consumer.accept(correct));

        addStroopGame(correct);
        createRandomGame();
    }
}
