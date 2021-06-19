package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.Game;

import lombok.Getter;

public class WithStimulationViewModel extends AndroidViewModel {

    // ID = -1 means that no Button is selected.
    // According to this logic of Android in the UI no selection will be shown.
    private static final Integer NO_BUTTON_SELECTED_ID = -1;

    @Getter
    private LiveData<String> stimulationString;
    @Getter
    private MutableLiveData<Integer> stimulationInt;
    @Getter
    private MutableLiveData<Integer> stimulationTypeButtonId;
    @Getter
    private boolean stimulated;

    private final String cortical;
    private final String epidural;

    public WithStimulationViewModel(@NonNull Application application) {
        super(application);

        cortical = application.getString(R.string.cortical);
        epidural = application.getString(R.string.epidural);

        stimulationInt = new MutableLiveData<>(0);
        stimulationString = Transformations.map(stimulationInt, s -> (s /2.0)+"");
        stimulationTypeButtonId = new MutableLiveData<>(NO_BUTTON_SELECTED_ID);

        stimulated = false;
    }

    public void resetStimulation() {
        stimulated = false;
    }

    public void stimulate() {
        stimulated = true;
    }

    public Double getStimulationNumeric() {
        return stimulationInt.getValue() / 2.0;
    }

    public String getStimulationType() {
        int id = stimulationTypeButtonId.getValue();

        if (id == R.id.radioButton_stimulation_type_cortical)
            return cortical;
        else if(id == R.id.radioButton_stimulation_type_epidural)
            return epidural;
        else
            return Game.NO_STIMULATION;
    }
}
