package com.app.craniowake.view.activityHelper;

import androidx.lifecycle.LifecycleOwner;

import com.app.craniowake.data.model.Patient;
import com.app.craniowake.data.model.stimulation.ThresholdTest;
import com.app.craniowake.data.model.stimulation.VerificationTest;
import com.app.craniowake.view.viewModel.PatientViewModel;

// TODO: Für die verwendeten Strings Propertyfiles verwenden
public class ModelFactory {
    private ModelFactory(){}

    private static final String EMPTY_STRING = "";
    private static final String CORTICAL = "cortical";
    private static final String MONOPOLAR = "monopolar";
    private static final String EPIDURAL = "epidural";
    private static final String CLINICAL = "clinical";


    public static Patient create(LifecycleOwner lifecycleOwner, PatientViewModel model) {
        Patient patient = new Patient();
        model.getGender().observe(lifecycleOwner, gender -> patient.setSex(gender));
        model.getBirthdate().observe(lifecycleOwner, birthDate -> patient.setBirthDate(birthDate));
        model.getFirstname().observe(lifecycleOwner, firstname -> patient.setFirstname(firstname));
        model.getNumericalCasenumber().observe(lifecycleOwner, casenumber -> patient.setCaseNumber(casenumber));

        return patient;
    }

    public static VerificationTest emptyVerificationTest(String responseInMuscle){
//        return new VerificationTest(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, responseInMuscle, 0.0, EMPTY_STRING, 0.0);
        return new VerificationTest(MONOPOLAR, CORTICAL, CLINICAL, responseInMuscle, CORTICAL);
    }

    public static ThresholdTest emptyThresholdTest(String responseInMuscle){
        return new ThresholdTest(EPIDURAL, CLINICAL, responseInMuscle);
    }
}
