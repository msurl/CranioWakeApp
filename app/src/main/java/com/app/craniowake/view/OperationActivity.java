package com.app.craniowake.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Complication;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.activityHelper.customUtils.DialogEmergency;
import com.app.craniowake.view.games.CalculusActivity;
import com.app.craniowake.view.games.DigitalSpanMemoryActivity;
import com.app.craniowake.view.games.FourSquareActivity;
import com.app.craniowake.view.games.LineBisectionActivity;
import com.app.craniowake.view.games.PictureActivity;
import com.app.craniowake.view.games.PpttActivity;
import com.app.craniowake.view.games.ReactionActivity;
import com.app.craniowake.view.games.ReadActivity;
import com.app.craniowake.view.games.StroopActivity;
import com.app.craniowake.view.games.TokenActivity;
import com.app.craniowake.view.games.TrailMakingActivity;
import com.app.craniowake.view.stimulation.TreshholdTestActivity;
import com.app.craniowake.view.stimulation.VerificationTestActivity;
import com.app.craniowake.view.viewModel.ComplicationViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;
/**
 * This Activity is the menu and contains ever test to be performed.
 * complications are handled in this Activity.
 * BaseClass to all the TestActivities
 */
public class OperationActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * this part is not part of CranioWake 1.0 yet. Its the implementation of the Seekbar needed in cranioWake 2.0
     */
    protected SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {

        @SuppressLint("SetTextI18n")
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            TextView textView = findViewById(R.id.current_value);
            progress++;
            textView.setText("" + (progress / 2));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    OperationViewModel operationViewModel;
    ComplicationViewModel complicationViewModel;

    /**
     * creates OperationActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        initializeToolbar(R.id.toolbar);

        TextView currentOperationMode = findViewById(R.id.current_operation_status);
        final TextView currentPatient = findViewById(R.id.patient_id_operation_mode);

        currentOperationMode.setText(getCurrentOperationMode());
        if(getPatientName() != null){
            currentPatient.setText(getPatientName());
        } else {
            currentPatient.setText(R.string.no_patient_selected);
        }
    }

    /**
     * collection on Buttons to start a new test Activity
     * @param v id of the button which passes us on to the next test Activity.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.token_button:
                intent = new Intent(this, TokenActivity.class);
                break;
            case R.id.calc_button:
                intent = new Intent(this, CalculusActivity.class);
                break;
            case R.id.read_button:
                intent = new Intent(this, ReadActivity.class);
                break;
            case R.id.picture_button:
                intent = new Intent(this, PictureActivity.class);
                break;
            case R.id.pptt_button:
                intent = new Intent(this, PpttActivity.class);
                break;
            case R.id.stroop_button:
                intent = new Intent(this, StroopActivity.class);
                break;
            case R.id.four_square_button:
                intent = new Intent(this, FourSquareActivity.class);
                break;
            case R.id.digital_span_button:
                intent = new Intent(this, DigitalSpanMemoryActivity.class);
                break;
            case R.id.trailway_button:
                intent = new Intent(this, TrailMakingActivity.class);
                break;
            case R.id.middle_of_line_button:
                intent = new Intent(this, LineBisectionActivity.class);
                break;
            case R.id.reaction_button:
                intent = new Intent(this, ReactionActivity.class);
                break;
            default:
                break;
        }
        assert intent != null;
        intent.putExtra(IntentHolder.OPERATION_DATE, getCurrentOperationDate());
        startActivity(intent);
    }

    /**
     * the following information is displayed at the top of the operationActivity
     */

    private String getPatientName() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.PATIENT_NAME);
    }

    private String getCurrentOperationDate() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_DATE);
    }

    private String getCurrentOperationMode() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_MODE);
    }

    /**
     * creates object of a Complication and saves it with its operation reference to the database. Object is processed by the ComplicationViewModel
     */
    private void saveComplication() {
        complicationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ComplicationViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationDate()).observe(this, operation -> {
            try {
                Complication complication = new Complication(operation.getFkPatientId());
                complicationViewModel.addComplication(complication);
            } catch (Exception e) {
                System.out.println("complication has not been added to db");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sos_bar, menu);
        return true;
    }

    /**
     * when clicked a complication is added to the patients db
     * @param item emergency button in toolbar
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        saveComplication();
        displayAddedEmergency();
        return super.onOptionsItemSelected(item);
    }

    private void displayAddedEmergency() {
        DialogEmergency alert = new DialogEmergency();
        alert.showDialog(this);
    }

    /**
     * this part is not part of CranioWake 1.0 yet. Its the implementation of the Seekbar needed in cranioWake 2.0
     */
    public void startTreshholdTest(View view) {
        Intent intent = new Intent(this, TreshholdTestActivity.class);
        startActivity(intent);
    }

    /**
     * this part is not part of CranioWake 1.0 yet. Its the implementation of the Seekbar needed in cranioWake 2.0
     */
    public void startVerificationTest(View view) {
        Intent intent = new Intent(this, VerificationTestActivity.class);
        startActivity(intent);
    }

    protected void initializeToolbar(int id) {
        Toolbar toolbar = findViewById(id);
        setSupportActionBar(toolbar);
    }

    protected void initiateSeekbar() {
        SeekBar seek_bar = findViewById(R.id.slider_stimulation);
        seek_bar.setOnSeekBarChangeListener(listener);
    }

    /**
     * destroys activity and brings us back to main Activity
     */
    public void closeActivity(View view) {
        finish();
    }
}