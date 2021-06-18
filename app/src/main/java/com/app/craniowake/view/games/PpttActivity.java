package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.PpttGame;
import com.app.craniowake.databinding.ActPpttTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.PpttViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the PPT Test
 */
public class PpttActivity extends OperationActivity {

    PpttViewModel ppttViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActPpttTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_pptt_test);
        ppttViewModel = new ViewModelProvider(this).get(PpttViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(ppttViewModel);
        // das hier an der Stelle nochmal überdenken
        ppttViewModel.setOperationId(getCurrentOperationId());
        // TODO: An dieser Stelle prüfen, ob es zulässig ist, dass das ViewModel eine (indirekte) Referenz auf die zugehörige Activity hält. Ansonsten onClickListener in die Activity verlegen!
        ppttViewModel.addAnswerConsumer(this::playSound);

        setUiElements();
    }

    private void setUiElements() {
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    public void playSound(boolean correctAnswer) {
        if (correctAnswer)
            playSuccessSound();
        else
            playWrongSound();
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishPPTTGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        int correctAnswers = ppttViewModel.getCorrectAnswers();
        int wrongAnswers = ppttViewModel.getWrongAnswers();
        int runs = correctAnswers + wrongAnswers;

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.pptt_test));
        intent.putExtra(IntentHolder.RUNS, runs);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}