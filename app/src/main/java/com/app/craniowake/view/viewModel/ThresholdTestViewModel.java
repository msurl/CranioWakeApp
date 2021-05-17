package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.R;
import com.app.craniowake.data.model.stimulation.ThresholdTest;
import com.app.craniowake.data.repositories.ThresholdRepository;
import com.app.craniowake.view.activityHelper.ModelFactory;

import lombok.Getter;

public class ThresholdTestViewModel extends AndroidViewModel {

    private static final String EMPTY_STRING = "";

    @Getter
    private MutableLiveData<ThresholdTest> thresholdTest;
    @Getter
    private MutableLiveData<String> motorCharge;
    @Getter
    private MutableLiveData<String> speechCharge;
    @Getter
    private MutableLiveData<String[]> muscles;

    private final ThresholdRepository thresholdRepository;

    public ThresholdTestViewModel(@NonNull Application application) {
        super(application);
        this.thresholdRepository = new ThresholdRepository(application);


        String muscles[] = application.getResources().getStringArray(R.array.muscles);
        this.muscles = new MutableLiveData<>(muscles);

        this.thresholdTest = new MutableLiveData<>(ModelFactory.emptyThresholdTest(muscles[0]));
        this.motorCharge = new MutableLiveData<>(EMPTY_STRING);
        this.speechCharge = new MutableLiveData<>(EMPTY_STRING);
    }

    public void addMotorStimulation() {
        ThresholdTest test = thresholdTest.getValue();
        String charge = motorCharge.getValue();

        test.addMotorStimulation(charge);
//        thresholdTest.setValue(test.withMotorStimulation(charge));
    }

    public void addSpeechStimulation() {
        ThresholdTest test = thresholdTest.getValue();
        String charge = speechCharge.getValue();

        test.addSpeechStimulation(charge);
//        thresholdTest.setValue(test.withMotorStimulation(charge));
    }

    public void insert() {
        thresholdRepository.insert(thresholdTest.getValue());
    }
}
