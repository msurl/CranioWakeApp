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
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Patient;
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
        birthdayPicker = new BirthdayPicker(this, R.id.user_birthday_id);
    }

    public void addPatient(View view) {
        getDisplayInput();
        if (checkIfInputEmpty()) {
            savePatientToDb();
            displayNoteOfDbExport();
        }
    }

    private void getDisplayInput() {
        caseNumberInput = findViewById(R.id.user_input_caseNumber_id);
        firstNameInput = findViewById(R.id.user_input_firstname_id);
        lastNameInput = findViewById(R.id.user_input_lastname_id);
        birthdayInput = findViewById(R.id.user_birthday_id);
    }

    /**
     * gets input from fields and saves patient to database.
     * id is given to Intent
     */
    private void savePatientToDb() {
        long caseNumber = Long.parseLong(caseNumberInput.getText().toString());
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String birthday = birthdayInput.getText().toString();

        Patient patient = new Patient(caseNumber, lastName, firstName, birthday, checkPatientGender());
        addIntent(patient.getPatientId());
        patientViewModel.addPatient(patient);
    }

    /**
     * checks which radiobutton has been clicked
     *
     * @return sex of patient
     */
    private String checkPatientGender() {
        RadioButton maleRadioButton = findViewById(R.id.input_user_male);
        RadioButton femaleRadioButton = findViewById(R.id.input_user_female);

        if (femaleRadioButton.isChecked()) {
            return "female";
        } else if (maleRadioButton.isChecked()) {
            return "male";
        }
        return "no_gender";
    }

    /**
     * checks if one of the fields has been left empty. Saving is denied until every field is filled
     *
     * @return if a field is emtpy or not
     */
    private boolean checkIfInputEmpty() {
        RadioGroup radiogroup = findViewById(R.id.RGroup);
        int id = radiogroup.getCheckedRadioButtonId(); // funktion gibt -1 zurück, wenn keiner der Radiobuttons ausgewählt wurde

        if (TextUtils.isEmpty(caseNumberInput.getText()) || TextUtils.isEmpty(firstNameInput.getText()) || TextUtils.isEmpty(lastNameInput.getText()) || TextUtils.isEmpty(birthdayInput.getText()) || id == -1) {
            Toast.makeText(this, "please fill the form correctly. Patient has not been saved", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
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

}
