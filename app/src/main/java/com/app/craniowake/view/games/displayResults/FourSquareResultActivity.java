package com.app.craniowake.view.games.displayResults;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.craniowake.R;
import com.app.craniowake.view.activityHelper.IntentHolder;
/**
 * Results of the following Tests are Displayed: FourSquareActivity
 */
public class FourSquareResultActivity extends BaseResultActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_four_square);
        setFourSquareBaseResult();
    }

    /**
     * retrieves Results for Tests from Intents
     */
    private void setFourSquareBaseResult() {
        Intent intent = getIntent();

        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        int runs = intent.getIntExtra(IntentHolder.RUNS, 0);
        int correctAnsw = intent.getIntExtra(IntentHolder.CORRECT_ANSWERS, 0);
        int wrongAnsw = intent.getIntExtra(IntentHolder.WRONG_ANSWERS, 0);
        int square1 = intent.getIntExtra(IntentHolder.FIRST_SQUARE, 0);
        int square2 = intent.getIntExtra(IntentHolder.SECOND_SQUARE, 0);
        int square3 = intent.getIntExtra(IntentHolder.THIRD_SQUARE, 0);
        int square4 = intent.getIntExtra(IntentHolder.FOURTH_SQUARE, 0);

        displayFourSquareResult(gameName, runs, correctAnsw, wrongAnsw, square1, square2, square3, square4);
        setGameMode(getString(R.string.all_modes));
    }

    /**
     * displays only the results from object mode
     *  @param view method is triggered when clicked
     */
    public void setResultMode1FourSquare(View view) {
        Intent intent = getIntent();
        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        int runs = intent.getIntExtra(IntentHolder.RUNS_MODE_1, 0);
        int correctAnsw = intent.getIntExtra(IntentHolder.CORRECT_ANSWERS_MODE_1, 0);
        int wrongAnsw = intent.getIntExtra(IntentHolder.WRONG_ANSWERS_MODE_1, 0);

        int square1 = intent.getIntExtra(IntentHolder.FIRST_SQUARE_MODE_1, 0);
        int square2 = intent.getIntExtra(IntentHolder.SECOND_SQUARE_MODE_1, 0);
        int square3 = intent.getIntExtra(IntentHolder.THIRD_SQUARE_MODE_1, 0);
        int square4 = intent.getIntExtra(IntentHolder.FOURTH_SQUARE_MODE_1, 0);

        displayFourSquareResult(gameName, runs, correctAnsw, wrongAnsw, square1, square2, square3, square4);
        setGameMode(getString(R.string.objects));
    }

    /**
     * displays only the results from faces mode
     *  @param view method is triggered when clicked
     */
    public void setResultMode2FourSquare(View view) {
        Intent intent = getIntent();
        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        int runs = intent.getIntExtra(IntentHolder.RUNS_MODE_2, 0);
        int correctAnsw = intent.getIntExtra(IntentHolder.CORRECT_ANSWERS_MODE_2, 0);
        int wrongAnsw = intent.getIntExtra(IntentHolder.WRONG_ANSWERS_MODE_2, 0);

        int square1 = intent.getIntExtra(IntentHolder.FIRST_SQUARE_MODE_2, 0);
        int square2 = intent.getIntExtra(IntentHolder.SECOND_SQUARE_MODE_2, 0);
        int square3 = intent.getIntExtra(IntentHolder.THIRD_SQUARE_MODE_2, 0);
        int square4 = intent.getIntExtra(IntentHolder.FOURTH_SQUARE_MODE_2, 0);

        displayFourSquareResult(gameName, runs, correctAnsw, wrongAnsw, square1, square2, square3, square4);
        setGameMode(getString(R.string.faces));
    }

    /**
     * displays results from entire Test
     *  @param square1 correct Answers made in this particular square
     *  @param square2 correct Answers made in this particular square
     *  @param square3 correct Answers made in this particular square
     *  @param square4 correct Answers made in this particular square
     */
    @SuppressLint("SetTextI18n")
    void displayFourSquareResult(String gameName, int runs, int correctAnsw, int wrongAnsw, int square1, int square2, int square3, int square4) {
        TextView inputGameName = findViewById(R.id.inputGameName);
        TextView inputRuns = findViewById(R.id.inputRuns);
        TextView inputCorrectAnswers = findViewById(R.id.inputCorrectAnswer);
        TextView inputWrongAnswers = findViewById(R.id.inputWrongAnswer);
        TextView inputSquare1 = findViewById(R.id.inputFirstSquare);
        TextView inputSquare2 = findViewById(R.id.inputSecondSquare);
        TextView inputSquare3 = findViewById(R.id.inputThirdSquare);
        TextView inputSquare4 = findViewById(R.id.inputFourthSquare);
        TextView inputPercentage = findViewById(R.id.inputPercentage);

        inputGameName.setText(gameName);
        inputRuns.setText(Integer.toString(runs));
        inputSquare1.setText(square1 + "/" + runs);
        inputSquare2.setText(square2 + "/" + runs);
        inputSquare3.setText(square3 + "/" + runs);
        inputSquare4.setText(square4 + "/" + runs);

        inputCorrectAnswers.setText(Integer.toString(correctAnsw));
        inputWrongAnswers.setText(Integer.toString(wrongAnsw));
        inputPercentage.setText(calcPercent(correctAnsw, (runs * 4)) + "%");
    }

    private void setGameMode(String gameMode) {
        TextView inputGameMode = findViewById(R.id.inputGameMode);
        inputGameMode.setText(gameMode);
    }

    public void getBaseResult(View view) {
        setFourSquareBaseResult();
    }
}
