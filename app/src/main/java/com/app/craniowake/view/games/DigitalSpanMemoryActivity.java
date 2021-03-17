package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.DigitalSpanMemoryViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;

import java.util.Random;

/**
 * Implementation of the Digital Span Memory Test
 */
public class DigitalSpanMemoryActivity extends OperationActivity {

    OperationViewModel operationViewModel;
    DigitalSpanMemoryViewModel digitalSpanMemoryViewModel;

    volatile boolean shutdown = false;
    private int[] numberSpectrum;
    private int flags;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_digital_span_memory_test);
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * starts to show a new set of animation with three numbers
     *
     * @param view method is called when middle area of the screen is clicked
     */
    public void startNextSetOfNumbers(View view) {
        shutdown = false;
        TextView btn = findViewById(R.id.dig_span_num);
        Handler mHandler = new Handler();
        if (btn.getText().toString().equals("start")) {
            btn.setText("  ");
            startShowingNumbers(mHandler);
        }
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "true" or "false" is clicked
     */
    public void setAnswer(View view) {
        shutdown = true;
        saveDigitalSpanMemoryAnswer(getAnswer(view.getId()));
        setTextViewToStart();
    }

    /**
     * creates object of DigitalSpanMemoryGame and saves the answer to the database. Object is processed by the DigitalSpanMemoryViewModel
     *
     * @param answer answer given by patient
     */
    private void saveDigitalSpanMemoryAnswer(boolean answer) {
        digitalSpanMemoryViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(DigitalSpanMemoryViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                DigitalSpanMemoryGame digitalSpanMemoryGame = new DigitalSpanMemoryGame(answer, operation.getOperationId());
                digitalSpanMemoryViewModel.addDigitalSpanMemoryGame(digitalSpanMemoryGame);
            } catch (Exception e) {
                System.out.println("DigitalSpanGame has not been added to db");
            }
        });
    }

    /**
     * evaluates answer given by the id of the button which was clicked. Also increases general counter of correct or wrong answers
     *
     * @param id of button "true" or "false"
     */
    @SuppressLint("NonConstantResourceId")
    private boolean getAnswer(int id) {
        switch (id) {
            case R.id.digSpanTrueButton:
                correctAnswers++;
                return true;
            case R.id.digSpanFalseButton:
                wrongAnswers++;
                return false;
            default:
                break;
        }
        return false;
    }

    private void setTextViewToStart() {
        TextView textView = findViewById(R.id.dig_span_num);
        textView.setText(R.string.start);
    }

    /**
     * starts animation
     */
    private void startShowingNumbers(Handler mHandler) {
        runAnimation(setNumberOnView(), mHandler);
    }

    private void generateNumbers() {
        this.numberSpectrum = new int[10];
        for (int i = 0; i < 10; i++) {
            this.numberSpectrum[i] = i;
        }
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
     * generates random number from pool of 0-10
     */
    private int createCurrentNumber() {
        generateNumbers();
        Random random = new Random();
        return numberSpectrum[random.nextInt(numberSpectrum.length - 1)];
    }

    private TextView setNumberOnView() {
        return findViewById(R.id.dig_span_num);
    }


    /**
     * creates thread to simulate and animation of the numbers appearing and disappearing on screen
     *
     * @param textView view on which the changing numbers are displayed
     * @param mHandler allows to send and process message and runnable objects associated with a thread's MessageQueue
     */
    private void runAnimation(TextView textView, final Handler mHandler) {
        flags = 0;
        final TextView currentView = textView;
        new Thread(() -> {
            int i = 1;
            while (!shutdown && i < 7) {
                try {
                    mHandler.post(() -> {
                        if (flags == 0) {
                            int tmp = createCurrentNumber();
                            currentView.setText(Integer.toString(tmp));
                            flags = 1;
                        } else {
                            currentView.setText("  ");
                            flags = 0;
                        }
                    });
                    Thread.sleep(1250);
                } catch (Exception e) {
                    System.out.println("fehler");
                }
                i++;
            }
        }).start();
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishSpanMemoryGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.dig_span_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}
