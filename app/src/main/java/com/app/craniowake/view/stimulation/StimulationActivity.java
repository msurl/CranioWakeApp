package com.app.craniowake.view.stimulation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.craniowake.R;
/**
 * THIS CLASS IS NOT PART OF THE FINISHED APP. CLASS IS FOR UPDATE TO CRANIOWAKE 2.0. PLEASE IGNORE
 */
public class StimulationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private boolean checkIfInputEmpty(int monopolarStimulationId, int ResponseId, TextView mAInput) {
        RadioGroup radiogroupStimulation = findViewById(monopolarStimulationId); //R.id.radiogroup_monopoloar_bipolar
        RadioGroup radiogroupResponse = findViewById(ResponseId); // RGroup_treshhold
        // funktion gibt -1 zurück, wenn keiner der Radiobuttons ausgewählt wurde

        if (TextUtils.isEmpty(mAInput.getText()) || radiogroupStimulation.getCheckedRadioButtonId() == -1 || radiogroupResponse.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "please fill the form correctly. Input has not been saved", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private String checkResponseInMuscle(RadioButton clinical, RadioButton emg, RadioButton both) {
        if (clinical.isChecked()) {
            return "clinical";
        } else if (emg.isChecked()) {
            return "emg";
        } else if (both.isChecked()) {
            return "clinical and emg";
        }
        return "no respone";
    }

    private void initializeSpinners() {
        //spinner1
        Spinner spinnerBrainArea = findViewById(R.id.spinnerBrainArea);
        createDropdownSpinner(spinnerBrainArea, R.array.brainAreas);

        //spinner2
        Spinner spinnerGameMode = findViewById(R.id.spinnerGameMode);
        createDropdownSpinner(spinnerGameMode, R.array.muscles);
    }

    private void createDropdownSpinner(Spinner spinner, int stringResourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, stringResourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void closeActivity(View vie) {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
