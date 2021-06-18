package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.StroopGame;
import com.app.craniowake.databinding.ActStroopTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.StroopViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the Stroop Test
 */
public class StroopActivity extends OperationActivity {

    StroopViewModel stroopViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stroopViewModel = new ViewModelProvider(this).get(StroopViewModel.class);
        stroopViewModel.setCurrentOperationId(getCurrentOperationId());
        // TODO: An dieser Stelle prüfen, ob es zulässig ist, dass das ViewModel eine (indirekte) Referenz auf die zugehörige Activity hält. Ansonsten onClickListener in die Activity verlegen!
        stroopViewModel.addAnswerConsumer(this::playSound);

        ActStroopTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_stroop_test);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(stroopViewModel);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    private void playSound(boolean correct) {
        if(correct)
            playSuccessSound();
        else
            playWrongSound();
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishStroopGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        int correctAnswers = stroopViewModel.getCorrectAnswers();
        int wrongAnswers = stroopViewModel.getWrongAnswers();

        intent.putExtra(IntentHolder.GAME_NAME, "Stroop Test");
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}
