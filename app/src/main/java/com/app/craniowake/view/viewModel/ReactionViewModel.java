package com.app.craniowake.view.viewModel;

import android.app.Application;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.data.model.gameModels.Game;
import com.app.craniowake.data.model.gameModels.ReactionGame;
import com.app.craniowake.data.repositories.ReactionRepository;
import com.app.craniowake.view.activityHelper.StopWatch;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the ReactionActivity and is used as an abstraction Layer
 */
public class ReactionViewModel extends WithStimulationViewModel {
    private final ReactionRepository reactionRepository;

    private final static ColorStateList RED = ColorStateList.valueOf(Color.RED);
    private final static ColorStateList BLUE = ColorStateList.valueOf(Color.BLUE);

    @Getter
    private MediatorLiveData<ColorStateList> backgroundLiveData;

    @Getter
    @Setter
    private MutableLiveData<Boolean> shutdown;
    @Getter
    @Setter
    private MutableLiveData<Boolean> clicked;
    @Getter
    @Setter
    private MutableLiveData<Boolean> started;
    @Getter
    @Setter
    private int counter = 0;

    @Getter
    private StopWatch stopWatch;

    // 5 "-" signs
    @Getter
    private final String[] rounds = new String[] {"-", "-", "-", "-", "-"};


    public ReactionViewModel(@NonNull Application application) {
        super(application);
        reactionRepository = new ReactionRepository(application);

        stopWatch = new StopWatch(new Handler(Looper.myLooper()));

        backgroundLiveData = new MediatorLiveData<>();

        started = new MutableLiveData<>(false);
        clicked = new MutableLiveData<>(false);
        shutdown = new MutableLiveData<>(false);

        backgroundLiveData.addSource(clicked, c -> backgroundLiveData.setValue(background(c, started.getValue())));
        backgroundLiveData.addSource(started, s -> backgroundLiveData.setValue(background(clicked.getValue(), s)));
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param reactionGame is generated in ReactionActivity and send to be saved to db
     */
    public void addReactionGame(ReactionGame reactionGame) {
        reactionRepository.insert(reactionGame);
    }

    public void incrementCounter(){
        counter++;
    }

    private static ColorStateList background(boolean clicked, boolean started) {
        if(!clicked && !started) {
            return RED;
        }
        else {
            return BLUE;
        }
    }

    public void addReactionGame(Long operationId) {
        addReactionGame(new ReactionGame(stopWatch.getMilliSeconds(), getStimulationNumeric(), getStimulationType(), operationId));
    }
}
