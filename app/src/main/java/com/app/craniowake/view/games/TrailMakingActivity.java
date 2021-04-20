package com.app.craniowake.view.games;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.TrailMakingGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.ModiBaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.TrailMakingViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Implementation of the Trail Making Test
 */
public class TrailMakingActivity extends OperationActivity {

    private final String[] alphNumPoints = {"1", "A", "2", "B", "3", "C", "4", "D", "5", "E"};
    private final String[] numbersPoints = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final boolean[] answers = {false, false, false, false, false, false, false, false, false, false};
    private TrailMakingViewModel trailMakingViewModel;
    private ArrayList<TextView> textViewNumbers;
    private int position = 0;
    private int correctAnswersMode1;
    private int falseAnswersMode1;
    private int correctAnswersMode2;
    private int falseAnswersMode2;
    private int gameMode = 1;
    private float width;
    private float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_trailway_test);
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
        setupGameView();

        Button button = findViewById(R.id.next_set_dots);
        button.setOnClickListener(v -> {
            calcCorrectAnswers();
            setGameMode();
        });
    }

    /**
     * sets game to default to start again
     *
     * @param view method is triggered when "restart" Button is clicked
     */
    public void restartLines(View view) {
        position = 0;
        setAnswerCounterPerGameToFalse();
        for (TextView textView : textViewNumbers) {
            textView.setTextColor(Color.RED);
        }
    }

    /**
     * following two methods sets mode to 2=numbers+alphabet or 1=numbers
     *
     * @param view method is triggered when "change mode" Button is clicked
     */
    public void setTrailwayGameMode(View view) {
        saveAndSetToDefault();
        if (gameMode > 1) {
            gameMode = 1;
        } else {
            gameMode++;
        }
        setGameMode();
    }

    private void setGameMode() {
        position = 0;
        switch (gameMode) {
            case 1:
                setInput(numbersPoints);
                break;
            case 2:
                setInput(alphNumPoints);
                break;
            default:
                break;
        }
    }

    /**
     * counts how many elements of the boolean array are true
     *
     * @return amount of correctly connected points per round
     */
    private int getCorrectAnswersPerRound() {
        int answerCounterPerRound = 0;
        for (boolean answer : answers) {
            if (answer) {
                answerCounterPerRound++;
            }
        }
        return answerCounterPerRound;
    }

    /**
     * checks if amount of correctly connected points per round is 10
     *
     * @return if round has been answered correctly
     */
    private boolean calcCorrectAnswers() {
        int answerCounterPerRound = getCorrectAnswersPerRound();
        if (answerCounterPerRound > 9) {
            if (gameMode == 1) {
                correctAnswersMode1++;
            }
            if (gameMode == 2) {
                correctAnswersMode2++;
            }
            return true;
        } else {
            if (gameMode == 1) {
                falseAnswersMode1++;
            }
            if (gameMode == 2) {
                falseAnswersMode2++;
            }
        }
        return false;
    }

    private void saveAndSetToDefault() {
        saveTrailwayGame(calcCorrectAnswers());
        setAnswerCounterPerGameToFalse();
    }

    /**
     * compares clicked element to the position it should have been. Comparison of two arrays to determine correct location of answer
     *
     * @param textView touched element
     */
    private void evaluateAnswer(TextView textView) {
        String[] checkAlpNumArray = getResources().getStringArray(R.array.numbers_letters);
        String[] checkNumArray = getResources().getStringArray(R.array.numbers);

        switch (gameMode) {
            case 1:
                if (textView.getText().equals(checkNumArray[position])) {
                    answers[position] = true;
                }
                break;
            case 2:
                if (textView.getText().equals(checkAlpNumArray[position])) {
                    answers[position] = true;
                }
                break;
            default:
                System.out.println("keine übereinstimmung");
                break;
        }
    }

    /**
     * set answers to default per round
     */
    private void setAnswerCounterPerGameToFalse() {
        for (int i = 0; i < 10; i++) {
            answers[i] = false;
        }
    }

    /**
     * creates object of TrailMakingGame and saves the answer to the database. Object is processed by the TrailMakingViewModel
     *
     * @param answer if patient has connected points correctly or not
     */
    private void saveTrailwayGame(boolean answer) {
        trailMakingViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TrailMakingViewModel.class);
        OperationViewModel operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                TrailMakingGame trailMakingGame = new TrailMakingGame(answer, operation.getFkPatientId());
                trailMakingViewModel.addTrailwayGame(trailMakingGame);
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
     * create numbers on view programmatically
     *
     * @param x     position of TextView
     * @param y     position of TextView
     * @param input number or sign of element
     */
    private TextView createNumberView(int x, int y, String input) {

        final TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(input);
        textView.setX(x);
        textView.setY(y);
        textView.setTextSize(70);
        textView.setClickable(true);
        textView.setTextColor(Color.RED);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setOnClickListener(v -> {
            if (textView.getCurrentTextColor() == Color.RED) {
                textView.setTextColor(Color.GREEN);
                evaluateAnswer(textView);
                position++;
            } else {
                textView.setTextColor(Color.RED);
                position--;
                answers[position] = false;
            }
        });
        return textView;
    }

    /**
     * shuffles Array and creates random distribution for elements
     *
     * @param input array of elements to be clicked
     */
    private void setInput(String[] input) {
        Collections.shuffle(Arrays.asList(input));
        int i = 0;
        for (TextView textView : textViewNumbers) {
            textView.setText(input[i]);
            textView.setTextColor(Color.RED);
            i++;
        }
    }

    private void setFrameLayout() {
        FrameLayout frameLayout = findViewById(R.id.frame_connect);
        if (frameLayout != null) {
            for (TextView textView : textViewNumbers) {
                frameLayout.addView(textView);
            }
        }
    }

    /**
     * resizes position on screen depending on screensize of device
     *
     * @param x default position on screen
     */
    private int transformCoordinateX(int x) {
        final float xResD = 800;
        float alpha = this.width / xResD;
        float res = alpha * x;
        return (int) res;
    }

    /**
     * analogue for y position
     */
    private int transformCoordinateY(int y) {
        final float yResD = 1280;
        float beta = this.height / yResD;
        float res = beta * y;
        return (int) res;
    }

    /**
     * resizes all elements
     */
    private void setArraylist() {
        int[][] numbersCoordinated = {{250, 600, 100, 450, 670, 240, 130, 400, 660, 250}, {40, 30, 190, 250, 240, 360, 500, 490, 570, 630}};
        textViewNumbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            textViewNumbers.add(createNumberView(transformCoordinateX(numbersCoordinated[0][i - 1]), transformCoordinateY(numbersCoordinated[1][i - 1]), Integer.toString(i)));
        }
    }

    /**
     * sets metrics depending of current device
     */
    private void setMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.width = dm.widthPixels;
        this.height = dm.heightPixels;
    }

    private void setupGameView() {
        setMetrics();
        setArraylist();
        setFrameLayout();
    }



    /**
     * saves results in an Intent and opens new ModiBaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishTrailwayGame(View view) {
        saveAndSetToDefault();
        Intent intent = new Intent(this, ModiBaseResultActivity.class);
        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.trailwaay_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswersMode1 + correctAnswersMode2) + (falseAnswersMode1 + falseAnswersMode2));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, (correctAnswersMode1 + correctAnswersMode2));
        intent.putExtra(IntentHolder.WRONG_ANSWERS, (falseAnswersMode1 + falseAnswersMode2));

        intent.putExtra(IntentHolder.RUNS_MODE_1, (correctAnswersMode1 + falseAnswersMode1));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_MODE_1, correctAnswersMode1);
        intent.putExtra(IntentHolder.WRONG_ANSWERS_MODE_1, falseAnswersMode1);

        intent.putExtra(IntentHolder.RUNS_MODE_2, (correctAnswersMode2 + falseAnswersMode2));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_MODE_2, correctAnswersMode2);
        intent.putExtra(IntentHolder.WRONG_ANSWERS_MODE_2, falseAnswersMode2);

        finish();
        startActivity(intent);
    }
}
