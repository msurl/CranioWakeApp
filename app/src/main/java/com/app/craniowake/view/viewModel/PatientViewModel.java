package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.craniowake.data.model.Patient;
import com.app.craniowake.data.repositories.PatientRepository;

import java.util.List;
/**
 * stores and manages UI-related data of the PatientActivity and is used as an abstraction Layer
 */
public class PatientViewModel extends AndroidViewModel {

    private final PatientRepository patientRepository;
    private final LiveData<List<Patient>> allPatients;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();
    }

    /**
     * passes the object to be saved in database to underlying repository
     * @param patient is generated in AddPatientActivity and send to be saved to db
     */
    public void addPatient(Patient patient) {
        patientRepository.insert(patient);
    }

    /**
     * send current Patient as a LiveData object to Activity
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
}
