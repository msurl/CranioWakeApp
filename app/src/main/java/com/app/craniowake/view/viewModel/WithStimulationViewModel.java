package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import lombok.Getter;

public class WithStimulationViewModel extends AndroidViewModel {

    @Getter
    private LiveData<Double> stimulation;

    @Getter
    private MutableLiveData<Integer> stimulationInt;
    @Getter
    protected boolean stimulated;

    public WithStimulationViewModel(@NonNull Application application) {
        super(application);
        stimulationInt = new MutableLiveData<>(4);

        stimulation = Transformations.map(stimulationInt, s -> s /2.0);

        stimulated = false;
    }

    public void resetStimulation() {
        stimulated = false;
    }

    public void stimulate() {
        stimulated = true;
    }
}
