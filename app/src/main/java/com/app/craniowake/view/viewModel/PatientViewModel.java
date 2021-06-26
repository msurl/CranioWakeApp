package com.app.craniowake.view.viewModel;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Patient;
import com.app.craniowake.data.repositories.PatientRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

/**
 * stores and manages UI-related data of the PatientActivity and is used as an abstraction Layer
 */
public class PatientViewModel extends AndroidViewModel {

    private final PatientRepository patientRepository;
    private final LiveData<List<Patient>> allPatients;

    private static final Map<String, Integer> radioButtonValuesToIds = new HashMap<String, Integer>() {{
        put("male", R.id.input_user_male);
        put("female", R.id.input_user_female);
        put("no_gender", -1);
}};

    @Getter
    private MutableLiveData<Patient> patient;

//    @Getter
    private LiveData<Boolean> validInput;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();

        Patient pat = new Patient();
        patient = new MutableLiveData<>(pat);

        validInput = Transformations.map(patient, p -> !TextUtils.isEmpty(p.getFirstname()) &&
                !TextUtils.isEmpty(p.getLastname()) && !TextUtils.isEmpty(p.getBirthDate()) &&
                p.getCaseNumber() != null && p.getCaseNumber() <= 3999999999L &&
                !TextUtils.isEmpty(p.getUniversity().getName()) && !TextUtils.isEmpty(p.getUniversity().getLocation()));
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param patient is generated in AddPatientActivity and send to be saved to db
     */
    public void addPatient(Patient patient) {
        patientRepository.insert(patient);
    }

    public Patient addCurrentPatient() {
        Patient p = patient.getValue();
        addPatient(p);
        return p;
    }

    /**
     * send current Patient as a LiveData object to Activity
     *
     * @param id return patient by this id
     */
    public LiveData<Patient> getPatientById(long id) {
        return patientRepository.getPatientById(id);
    }

    /**
     * send all Patients as a LiveData List to Activity
     */
    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    private static boolean nonEmptyString(String string) {
        return !TextUtils.isEmpty(string);
    }

    public Integer getRadioButtonId() {
        String sex = patient.getValue().getSex();

        int id = radioButtonValuesToIds.entrySet().stream().filter(vId -> vId.getKey().equals(sex)).
                mapToInt(Map.Entry::getValue).findAny().orElse(-1);

        return id;
    }

    public void setRadioButtonId(Integer checkedButtonId) {
        Patient patient = this.patient.getValue();

        String sex = radioButtonValuesToIds.entrySet().stream().
        filter(e -> e.getValue().equals(checkedButtonId)).
        map(Map.Entry::getKey).findAny().orElse(null);

        patient.setSex(sex);
        this.patient.setValue(patient);
    }

    public String getCaseNumber() {
        Long caseNumber = patient.getValue().getCaseNumber();

        if(caseNumber != null)
            return patient.getValue().getCaseNumber().toString();
        else
            return "";
    }

    public void setCaseNumber(String caseNumber) {
        Patient p = patient.getValue();
        Long l;
        try {
            l = Long.parseLong(caseNumber);
        }
        catch (NumberFormatException | NullPointerException e) {
            l = null;
        }

        p.setCaseNumber(l);
        patient.setValue(p);
    }

    public boolean isValidInput() {
        Patient p = patient.getValue();

        return !TextUtils.isEmpty(p.getFirstname()) && !TextUtils.isEmpty(p.getLastname()) &&
                !TextUtils.isEmpty(p.getBirthDate()) && p.getCaseNumber() != null &&
                p.getCaseNumber() <= 3999999999L && !TextUtils.isEmpty(p.getUniversity().getName()) &&
                !TextUtils.isEmpty(p.getUniversity().getLocation());

    }
}
