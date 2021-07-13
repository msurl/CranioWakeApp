package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.app.craniowake.R;
import com.app.craniowake.data.model.stimulation.ClinicalTest;
import com.app.craniowake.data.repositories.ClinicalTestRepository;

import lombok.Getter;

public class ClinicalTestViewModel extends AndroidViewModel {

    @Getter
    private MutableLiveData<ClinicalTest> clinicalTest;

    private ClinicalTestRepository repository;

    public ClinicalTestViewModel(@NonNull Application application) {
        super(application);
        repository = new ClinicalTestRepository(application);

        clinicalTest = new MutableLiveData<>(new ClinicalTest());
    }

    public void initializeTest() {
        clinicalTest.setValue(new ClinicalTest());
    }

    public void saveCurrentTest() {
        ClinicalTest test = clinicalTest.getValue();
        repository.insert(test);

        initializeTest();
    }

    public Integer getMotorFunctionLatentArmPalsyRadioButtonId() {
        return clinicalTest.getValue().getMotorFunction().getLatentArmPalsy() ? R.id.latent_arm_palsy_yes_button : R.id.latent_arm_palsy_no_button;
    }

    public void setMotorFunctionLatentArmPalsyRadioButtonId(Integer id) {
        ClinicalTest ct = clinicalTest.getValue();
        ct.getMotorFunction().setLatentArmPalsy(id == R.id.latent_arm_palsy_yes_button);

        clinicalTest.setValue(ct);
    }

    public Integer getMotorFunctionLatentLegPalsyRadioButtonId() {
        return clinicalTest.getValue().getMotorFunction().getLatentLegPalsy() ? R.id.latent_leg_palsy_yes_button : R.id.latent_leg_palsy_no_button;
    }

    public void setMotorFunctionLatentLegPalsyRadioButtonId(Integer id) {
        ClinicalTest ct = clinicalTest.getValue();
        ct.getMotorFunction().setLatentLegPalsy(id == R.id.latent_leg_palsy_yes_button);

        clinicalTest.setValue(ct);
    }
}
