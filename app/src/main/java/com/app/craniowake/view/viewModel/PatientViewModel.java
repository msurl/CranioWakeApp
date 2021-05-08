package com.app.craniowake.view.viewModel;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Patient;
import com.app.craniowake.data.repositories.PatientRepository;

import java.util.List;

import lombok.Getter;

/**
 * stores and manages UI-related data of the PatientActivity and is used as an abstraction Layer
 */
public class PatientViewModel extends AndroidViewModel {

    private final PatientRepository patientRepository;
    private final LiveData<List<Patient>> allPatients;

    @Getter
    private MutableLiveData<Integer> checkedButtonId;
    @Getter
    private MutableLiveData<String> caseNumber;
    @Getter
    private MutableLiveData<String> firstname;
    @Getter
    private MutableLiveData<String> lastname;
    @Getter
    private MutableLiveData<String> birthdate;

    @Getter
    private LiveData<String> gender;

    //    private LiveData<Boolean> emptyButton;
    private LiveData<Boolean> validCasenumber;
    private LiveData<Boolean> validFirstname;
    private LiveData<Boolean> validLastname;
    private LiveData<Boolean> validBirthdate;


    private static final String EMPTY_STRING = "";
    private static final Integer NO_SELECTION_ID = -1;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();
        checkedButtonId = new MutableLiveData<>(R.id.input_user_male);
        caseNumber = new MutableLiveData<>(EMPTY_STRING);
        firstname = new MutableLiveData<>(EMPTY_STRING);
        lastname = new MutableLiveData<>(EMPTY_STRING);
        birthdate = new MutableLiveData<>(EMPTY_STRING);
        gender = Transformations.map(checkedButtonId, id -> {
            if (id == -1)
                return "no_gender";
            if (id == R.id.input_user_male)
                return "male";
            else
                return "female";
        });

        validFirstname = Transformations.map(firstname, PatientViewModel::nonEmptyString);
        validLastname = Transformations.map(lastname, PatientViewModel::nonEmptyString);
        validBirthdate = Transformations.map(birthdate, PatientViewModel::nonEmptyString);
        validCasenumber = Transformations.map(caseNumber, cn -> {
            return Long.parseLong(cn) > 3999999999L || !PatientViewModel.nonEmptyString(cn);
        });

    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param patient is generated in AddPatientActivity and send to be saved to db
     */
    public void addPatient(Patient patient) {
        patientRepository.insert(patient);
    }

    // Option 1
    public Patient getPatientFromViewInput() {
        return new Patient(Long.parseLong(caseNumber.getValue()), firstname.getValue(), lastname.getValue(), birthdate.getValue(), gender.getValue());
    }

    /**
     * send current Patient as a LiveData object to Activity
     *
     * @param id return patient by this id
     */
    public LiveData<Patient> getPatientById(int id) {
        return patientRepository.getPatientById(id);
    }

    /**
     * send all Patients as a LiveData List to Activity
     */
    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }

//    public boolean isValidInput() {
//        return validCasenumber.getValue() && validFirstname.getValue() && validLastname.getValue() && validBirthdate.getValue();
//    }

    private static boolean nonEmptyString(String string) {
        return !TextUtils.isEmpty(string);
    }
}
