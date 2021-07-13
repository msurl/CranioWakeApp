package com.app.craniowake.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Complication;
import com.app.craniowake.view.activityHelper.CraniowakeDialogBuilder;
import com.app.craniowake.view.activityHelper.IntentHolder;
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
import com.app.craniowake.view.stimulation.ClinicalTestActivity;
import com.app.craniowake.view.stimulation.ThreshholdTestActivity;
import com.app.craniowake.view.stimulation.VerificationTestActivity;
import com.app.craniowake.view.viewModel.ComplicationViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * This Activity is the menu and contains ever test to be performed.
 * complications are handled in this Activity.
 * BaseClass to all the TestActivities
 */
public class OperationActivity extends AppCompatActivity implements View.OnClickListener {


    protected SoundPool soundPool;
    protected int beepSoundId;
    protected int successSoundId;
    protected int wrongSoundId;
    protected int failSoundId;
    protected Collection<Integer> soundsLoaded = new HashSet<>();

    protected double stimulation = 0.0;
    protected boolean stimulated = false;

    /**
     * this part is not part of CranioWake 1.0 yet. Its the implementation of the Seekbar needed in cranioWake 2.0
     */
    protected SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {

        @SuppressLint("SetTextI18n")
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            TextView textView = findViewById(R.id.current_value);
            stimulation = (double) progress / 2;
            textView.setText("" + stimulation);
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
        if (getPatientName() != null) {
            currentPatient.setText(getPatientName());
        } else {
            currentPatient.setText(R.string.no_patient_selected);
        }

        initSoundPool();
    }

    /**
     * collection on Buttons to start a new test Activity
     *
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

    protected Serializable getCurrentOperationDate() {
        Intent intent = getIntent();
        return intent.getSerializableExtra(IntentHolder.OPERATION_DATE);
    }

    protected long getCurrentOperationId() {
        Intent intent = getIntent();
        return intent.getLongExtra(IntentHolder.OPERATION_ID, -1l);
    }

    private String getCurrentOperationMode() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_MODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sos_bar, menu);
        return true;
    }

    /**
     * when clicked a complication is added to the patients db
     *
     * @param item emergency button in toolbar
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.sos_button)
            displayAddedComplication();
        else if(item.getItemId() == R.id.note_button)
            displayNoteDialog();
        return super.onOptionsItemSelected(item);
    }

    private void displayNoteDialog() {
        Dialog note = CraniowakeDialogBuilder.noteDialogWithDataBinding(this, this.getClass().getSimpleName(), getCurrentOperationId());
        note.show();
    }

    private void displayAddedComplication() {
        Dialog complication = CraniowakeDialogBuilder.complicationDialogWithDataBinding(this, getCurrentOperationId());
        complication.show();
    }

    /**
     * this part is not part of CranioWake 1.0 yet. Its the implementation of the Seekbar needed in cranioWake 2.0
     */
    public void startTreshholdTest(View view) {
        Intent intent = new Intent(this, ThreshholdTestActivity.class);
        intent.putExtra(IntentHolder.OPERATION_DATE, getCurrentOperationDate());
        startActivity(intent);
    }

    /**
     * this part is not part of CranioWake 1.0 yet. Its the implementation of the Seekbar needed in cranioWake 2.0
     */
    public void startVerificationTest(View view) {
        Intent intent = new Intent(this, VerificationTestActivity.class);
        startActivity(intent);
    }

    public void startClinicalTest(View view) {
        Intent intent = new Intent(this, ClinicalTestActivity.class);
        startActivity(intent);
    }


    protected void initializeToolbar(int id) {
        Toolbar toolbar = findViewById(id);
        setSupportActionBar(toolbar);
    }

    protected void initiateSeekbar() {
        SeekBar seek_bar = findViewById(R.id.slider_stimulation);
        seek_bar.setOnSeekBarChangeListener(listener);
        initializeStimulateButton();
    }

    protected void initSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(attributes).build();
        soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            if(status == 0)
                soundsLoaded.add(sampleId);
        });
        beepSoundId = soundPool.load(this, R.raw.beep, 1);
        successSoundId = soundPool.load(this, R.raw.success, 1);
        wrongSoundId = soundPool.load(this, R.raw.wrong, 1);
        failSoundId = soundPool.load(this, R.raw.fail, 1);
    }

    public void playBeepSound() {
        if(soundsLoaded.contains(beepSoundId))
            soundPool.play(beepSoundId, 1, 1, 1, 0, 1);
    }

    public void playSuccessSound() {
        if(soundsLoaded.contains(successSoundId))
            soundPool.play(successSoundId, 1, 1, 1, 0, 1);
    }

    public void playWrongSound() {
        if(soundsLoaded.contains(wrongSoundId))
            soundPool.play(wrongSoundId, 1, 1, 1, 0, 1);
    }

    public void playFailSound() {
        if(soundsLoaded.contains(failSoundId))
            soundPool.play(failSoundId, 1, 1, 1, 0, 1);
    }

    public void stimulate() {
        stimulated = true;
    }

    /**
     * destroys activity and brings us back to main Activity
     */
    public void closeActivity(View view) {
        finish();
    }

    public void initializeStimulateButton() {
        Button stimulate = findViewById(R.id.stimulate);
        stimulate.setOnClickListener(v -> stimulated = true);
    }
}