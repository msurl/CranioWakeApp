package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;
import com.app.craniowake.databinding.ActDigitalSpanMemoryTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.DigitalSpanMemoryViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the Digital Span Memory Test
 */
public class DigitalSpanMemoryActivity extends OperationActivity {

    DigitalSpanMemoryViewModel digitalSpanMemoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        digitalSpanMemoryViewModel = new ViewModelProvider(this).get(DigitalSpanMemoryViewModel.class);

        ActDigitalSpanMemoryTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_digital_span_memory_test);
        binding.setViewmodel(digitalSpanMemoryViewModel);
        binding.setLifecycleOwner(this);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * starts to show a new set of animation with three numbers
     *
     * @param view method is called when middle area of the screen is clicked
     */
    public void startNextSetOfNumbers(View view) {
        if(!digitalSpanMemoryViewModel.isStarted())
        {
            digitalSpanMemoryViewModel.setShutdown(false);
            Handler mHandler = new Handler();
            runAnimation(mHandler);
        }
    }

    public void correctAnswer(View view){
        this.digitalSpanMemoryViewModel.answer(true, getCurrentOperationId());
        playSuccessSound();
}

    public void wrongAnswer(View view){
        this.digitalSpanMemoryViewModel.answer(false, getCurrentOperationId());
        playSuccessSound();
    }

    /**
     * creates thread to simulate and animation of the numbers appearing and disappearing on screen
     *
     * @param mHandler allows to send and process message and runnable objects associated with a thread's MessageQueue
     */
    private void runAnimation(final Handler mHandler) {
        new Thread(() -> {
            digitalSpanMemoryViewModel.start();
            int i = 0;
            while (!digitalSpanMemoryViewModel.isShutdown() && i < 7) {
                try {
                    mHandler.post(() -> {
                        digitalSpanMemoryViewModel.incrementIndex();
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

        int correctAnswers = digitalSpanMemoryViewModel.getCorrectAnswers();
        int wrongAnswers = digitalSpanMemoryViewModel.getWrongAnswers();

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.dig_span_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}
