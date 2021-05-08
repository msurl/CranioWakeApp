package com.app.craniowake.view.patient;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Patient;
import com.app.craniowake.databinding.UserAddPatientBinding;
import com.app.craniowake.view.activityHelper.BirthdayPicker;
import com.app.craniowake.view.activityHelper.customUtils.DialogAddedPatient;
import com.app.craniowake.view.viewModel.PatientViewModel;

/**
 * Activity to add patients to database
 */
public class AddPatientActivity extends PatientActivity {

    BirthdayPicker birthdayPicker;
    private PatientViewModel patientViewModel;
    private EditText caseNumberInput;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText birthdayInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_patient);
        initializeNawigationDrawer();
        patientViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PatientViewModel.class);

        UserAddPatientBinding binding = DataBindingUtil.setContentView(this, R.layout.user_add_patient);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(patientViewModel);

        birthdayPicker = new BirthdayPicker(this, R.id.user_birthday_id ,patientViewModel.getBirthdate());
    }

    public void addPatient(View view) {
        if (isValidInput()) {
            savePatientToDb();
            displayNoteOfDbExport();
        }
        else {
            showInvalidInputToast();
        }
    }

    /**
     * gets input from fields and saves patient to database.
     * id is given to Intent
     */
    private void savePatientToDb() {
        // Option 2
        long caseNumber = Long.parseLong(patientViewModel.getCaseNumber().getValue());
        String firstName = patientViewModel.getFirstname().getValue();
        String lastName = patientViewModel.getLastname().getValue();
        String birthday = patientViewModel.getBirthdate().getValue();
//        String gender = patientViewModel.getGender().getValue();
        String gender = getGender();

        // Option 1
//        Patient patient = patientViewModel.getPatientFromViewInput();

        // Option 2
        Patient patient = new Patient(caseNumber, firstName, lastName, birthday, gender);
        addIntent(patient.getPatientId());
        patientViewModel.addPatient(patient);

        // Option 3 wäre es, direkt innehalb des ViewModels den Patienten zu erstellen, abzuspeichern und dann die ID zurüclzugeben.
    }

    /**
     * checks if one of the fields has been left empty. Saving is denied until every field is filled
     *
     * @return if a field is emtpy or not
     */
    private void showInvalidInputToast() {
      Toast.makeText(this, "please fill the form correctly. Patient has not been saved", Toast.LENGTH_LONG).show();
    }

    /**
     * opens custom dialog pop-up to inform user that patient has been successfully added
     */
    public void displayNoteOfDbExport() {
        DialogAddedPatient alert = new DialogAddedPatient();
        setTextOnDialoge();
        alert.showDialog(this);
    }

    private void setTextOnDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.event_note, null);
        builder.setView(v);
        TextView dialog_info = v.findViewById(R.id.text_dialog_information);
        dialog_info.setText(R.string.the_patient_has_been_added);
    }

    private boolean isValidInput() {
        return !TextUtils.isEmpty(patientViewModel.getCaseNumber().getValue())
                && !TextUtils.isEmpty(patientViewModel.getBirthdate().getValue())
                && !TextUtils.isEmpty(patientViewModel.getFirstname().getValue())
                && !TextUtils.isEmpty(patientViewModel.getLastname().getValue())
                && Long.parseLong(patientViewModel.getCaseNumber().getValue()) <= 3999999999L;
    }

    private String getGender() {
        return patientViewModel.getCheckedButtonId().getValue() == R.id.input_user_male ? "male" : "female";
    }
}
