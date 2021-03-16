package com.app.craniowake.view.games.displayResults;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.craniowake.R;
import com.app.craniowake.view.activityHelper.IntentHolder;
/**
 * Results of the following Tests are Displayed: DigitalSpanMemoryActivity, CalculusActivity, LineBisectionActivity, StroopActivity, PpttActivity and Token Activity
 * Is superclass to the other ResultActivities
 */
public class BaseResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_base);
        setResultBase();
    }

    /**
     * sets TextViews to display results
     * @param gameName name of the performed test
     * @param runs how many runs of the test have been made
     * @param correctAnsw total amount of correct answers for all rounds
     * @param wrongAnsw total amount of wrong answers for all rounds
     */
    @SuppressLint("SetTextI18n")
    void displayBaseResults(String gameName, int runs, int correctAnsw, int wrongAnsw) {

        TextView inputGameName = findViewById(R.id.inputGameName);
        TextView inputRuns = findViewById(R.id.inputRuns);
        TextView inputCorrectAnswers = findViewById(R.id.inputRightAnswer);
        TextView inputWrongAnswer = findViewById(R.id.inputWrongAnswer);
        TextView inputPercentage = findViewById(R.id.inputPercentage);

        inputGameName.setText(gameName);
        inputRuns.setText(Integer.toString(runs));
        inputCorrectAnswers.setText(Integer.toString(correctAnsw));
        inputWrongAnswer.setText(Integer.toString(wrongAnsw));
        inputPercentage.setText(calcPercent(correctAnsw, runs) + "%");
    }

    /**
     * retrieves Results for Tests from Intents
     */
    protected void setResultBase() {
        Intent intent = getIntent();

        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        int runs = intent.getIntExtra(IntentHolder.RUNS, 0);
        int correctAnsw = intent.getIntExtra(IntentHolder.CORRECT_ANSWERS, 0);
        int wrongAnsw = intent.getIntExtra(IntentHolder.WRONG_ANSWERS, 0);

        displayBaseResults(gameName, runs, correctAnsw, wrongAnsw);
    }

    /**
     * calculates the percentage of correct answers for the entire Test
     * @param correctAnswers total amount of correct answers for all rounds
     * @param runs how many runs of the test have been made
     */
    int calcPercent(int correctAnswers, int runs) {
        double percent = (double) correctAnswers / runs;
        double result = percent * 100;
        return (int) result;
    }

    /**
     * destroys activity from back stack and returns user to OperationActivity
     * @param view method is triggered when clicked
     */
    public void backToMenu(View view) {
        finish();
    }
}
