package com.app.craniowake.view.stimulation;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.app.craniowake.R;

/**
 * THIS CLASS IS NOT PART OF THE FINISHED APP. CLASS IS FOR UPDATE TO CRANIOWAKE 2.0. PLEASE IGNORE
 */
public class TreshholdTestActivity extends StimulationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_treshhold_test_stimulation);
    }

    /*private void initializeSpinners() {
        //spinner1
        Spinner spinnerBrainArea = findViewById(R.id.spinnerBrainArea);
        createDropdownSpinner(spinnerBrainArea, R.array.brainAreas);

        //spinner2
        Spinner spinnerGameMode = findViewById(R.id.spinnerGameMode);
        createDropdownSpinner(spinnerGameMode, R.array.game_modes);
    }*/

    private String checkMonopolarStimulation() {
        RadioButton maleRadioButton = findViewById(R.id.input_user_male);
        RadioButton femaleRadioButton = findViewById(R.id.input_user_female);

        if (femaleRadioButton.isChecked()) {
            return "female";
        } else if (maleRadioButton.isChecked()) {
            return "male";
        }
        return "no_gender";
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

    public void getTreshholdStimulationInput(View view) {

    }
}
