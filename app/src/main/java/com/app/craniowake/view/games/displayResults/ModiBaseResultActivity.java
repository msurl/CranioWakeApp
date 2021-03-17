package com.app.craniowake.view.games.displayResults;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.craniowake.R;
import com.app.craniowake.view.activityHelper.IntentHolder;

/**
 * Results of the following Tests are Displayed: PictureActivity and TrailMakingActivity
 */
public class ModiBaseResultActivity extends BaseResultActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_base_with_modi);
        setResultBase();

        TextView inputGameMode = findViewById(R.id.inputGameMode);
        inputGameMode.setText(getString(R.string.all_modes));
    }

    @Override
    public void setResultBase() {
        super.setResultBase();
    }

    /**
     * displays only the results from object or numbers mode
     *
     * @param view method is triggered when clicked
     */
    public void setResultMode1(View view) {
        Intent intent = getIntent();

        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        int runs = intent.getIntExtra(IntentHolder.RUNS_MODE_1, 0);
        int correctAnsw = intent.getIntExtra(IntentHolder.CORRECT_ANSWERS_MODE_1, 0);
        int wrongAnsw = intent.getIntExtra(IntentHolder.WRONG_ANSWERS_MODE_1, 0);

        displayBaseResults(gameName, runs, correctAnsw, wrongAnsw);
        setGameMode(gameName, getString(R.string.mode_1_objects), getString(R.string.mode_1_numbers));
    }

    /**
     * displays only the results from faces or numbers+alphabet mode
     *
     * @param view method is triggered when clicked
     */
    public void setResultMode2(View view) {
        Intent intent = getIntent();

        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        int runs = intent.getIntExtra(IntentHolder.RUNS_MODE_2, 0);
        int correctAnsw = intent.getIntExtra(IntentHolder.CORRECT_ANSWERS_MODE_2, 0);
        int wrongAnsw = intent.getIntExtra(IntentHolder.WRONG_ANSWERS_MODE_2, 0);

        displayBaseResults(gameName, runs, correctAnsw, wrongAnsw);
        setGameMode(gameName, getString(R.string.mode_2_faces), getString(R.string.mode_2_numbers));
    }

    /**
     * displays correct gameMode depending on which Test is performed
     *
     * @param gameName   Test which is performed
     * @param firstMode  name of mode of Picture Test
     * @param secondMode name of mode of Trail Making Test
     */
    private void setGameMode(String gameName, String firstMode, String secondMode) {
        TextView inputGameMode = findViewById(R.id.inputGameMode);

        if (gameName.equals(getString(R.string.picture_test))) {
            inputGameMode.setText(firstMode);
        } else {
            inputGameMode.setText(secondMode);
        }
    }

    /**
     * displays results from entire test
     *
     * @param view method is triggered when clicked
     */
    public void goToBaseResult(View view) {
        setResultBase();
        TextView inputGameMode = findViewById(R.id.inputGameMode);
        inputGameMode.setText(getString(R.string.all_modes));
    }

}
