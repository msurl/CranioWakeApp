package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.StroopGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.StroopViewModel;

import java.util.Random;

/**
 * Implementation of the Stroop Test
 */
public class StroopActivity extends OperationActivity {

    OperationViewModel operationViewModel;
    StroopViewModel stroopViewModel;
    private int tmpRandom;

    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_stroop_test);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
        startNextStroop();
    }

    private void startNextStroop() {
        Random random = new Random();
        TextView stroopView = findViewById(R.id.stroopink);
        setStroopText(random, stroopView);
        setStroopInk(random, stroopView);
    }

    /**
     * evaluate current answers and set next stroop on display
     *
     * @param view is called when "true" or "false" button is clicked
     */
    public void setNextStroop(View view) {
        evaluateAnswer(view);
        startNextStroop();
    }

    /**
     * displays random color of stroop text
     */
    private void setStroopText(Random random, TextView stroopView) {
        String[] stroopColors = getResources().getStringArray(R.array.colorNames);
        tmpRandom = random.nextInt(stroopColors.length);
        String tmpStroop = stroopColors[tmpRandom];
        stroopView.setText(tmpStroop);
    }

    private String getCurrentStroop(int index) {
        String[] stroopColors = getResources().getStringArray(R.array.colorNames);
        return stroopColors[index];
    }

    /**
     * displays random color of stroop ink
     */
    private void setStroopInk(Random random, TextView stroopView) {
        int[] rainbow = getResources().getIntArray(R.array.rainbow);
        tmpRandom = random.nextInt(rainbow.length);
        stroopView.setTextColor(rainbow[tmpRandom]);
    }

    @SuppressLint("NonConstantResourceId")
    private void evaluateAnswer(View view) {
        boolean answer = getPatientAnswer(view.getId());
        saveStroopGame(answer, getCurrentStroop(tmpRandom));
    }

    /**
     * evaluates answer given by the id of the button which was clicked. Also increases general counter of correct or wrong answers
     *
     * @param id of button "true" or "false"
     */
    @SuppressLint("NonConstantResourceId")
    private boolean getPatientAnswer(int id) {
        switch (id) {
            case R.id.stroopTrueButton:
                correctAnswers++;
                return true;
            case R.id.stroopFalseButton:
                wrongAnswers++;
                return false;
            default:
                break;
        }
        return false;
    }

    /**
     * creates object of StroopGame and saves the answer to the database. Object is processed by the StroopViewModel
     *
     * @param answer      of patient if color was correctly recognized
     * @param stroopColor the color to be recognized
     */
    private void saveStroopGame(boolean answer, String stroopColor) {
        stroopViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StroopViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                StroopGame stroopGame = new StroopGame(stroopColor, answer, operation.getFkPatientId());
                stroopViewModel.addStroopGame(stroopGame);
            } catch (Exception e) {
                System.out.println("PictureGame has not been added to db");
            }
        });
    }

    /**
     * returns string of dateTime when current operation was created t
     * its used as an identifier
     */
    private String getCurrentOperationId() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_DATE);
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishStroopGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, "Stroop Test");
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}
