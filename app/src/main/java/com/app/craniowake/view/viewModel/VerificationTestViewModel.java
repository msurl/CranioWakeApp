package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.R;
import com.app.craniowake.data.model.stimulation.VerificationStimulation;
import com.app.craniowake.data.model.stimulation.VerificationTest;
import com.app.craniowake.data.repositories.VerificationRepository;
import com.app.craniowake.view.activityHelper.ModelFactory;

import lombok.Getter;

public class VerificationTestViewModel extends AndroidViewModel {

    private VerificationRepository verificationRepository;
    @Getter
    private MutableLiveData<VerificationTest> verificationTest;
    @Getter
    private MutableLiveData<String> motorFunctionCharge;
    @Getter
    private MutableLiveData<String> speechFunctionCharge;

    private MutableLiveData<String[]> muscles;

    private static final String EMPTY = "";

    public VerificationTestViewModel(@NonNull Application application) {
        super(application);
        verificationRepository = new VerificationRepository(application);

        String muscles[] = application.getResources().getStringArray(R.array.muscles);
        this.muscles = new MutableLiveData<>(muscles);
        this.verificationTest = new MutableLiveData<>(ModelFactory.emptyVerificationTest(muscles[0]));
        this.motorFunctionCharge = new MutableLiveData<>(EMPTY);
        this.speechFunctionCharge = new MutableLiveData<>(EMPTY);
    }

    public LiveData<String[]> getMuscles(){
        return muscles;
    }

    public LiveData<VerificationTest> getByOperationId(int id) {
        return verificationRepository.getVerificationTestByOperationId(id);
    }

    public void addMotorStimulation() {
        VerificationTest test = verificationTest.getValue();
        String charge = motorFunctionCharge.getValue();

        // In diesem Fall wird keiner der Observer notified
        test.addMotorStimulation(charge);
        // Hier wird jeder Observer notified
//        verificationTest.setValue(test.withMotorStimulation(charge));
    }

    public void addSpeechStimulation() {
        VerificationTest test = verificationTest.getValue();
        String charge = speechFunctionCharge.getValue();

        // In diesem Fall wird keiner der Observer notified
        test.addSpeechStimulation(charge);
        // Hier wird jeder Observer notified
//        verificationTest.setValue(test.withSpeechStimulation(charge));
    }


    public void insert() {
        verificationRepository.insert(verificationTest.getValue());
    }
}
