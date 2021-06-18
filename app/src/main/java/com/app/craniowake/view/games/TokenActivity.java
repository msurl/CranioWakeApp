package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.TokenGame;
import com.app.craniowake.databinding.ActTokenTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.TokenViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the Token Test
 */
public class TokenActivity extends OperationActivity {

    TokenViewModel tokenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tokenViewModel = new ViewModelProvider(this).get(TokenViewModel.class);
        tokenViewModel.setCurrentOperationId(getCurrentOperationId());

        ActTokenTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_token_test);
        binding.setViewmodel(tokenViewModel);
        binding.setLifecycleOwner(this);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }


    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishTokenGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        int correctAnswers = tokenViewModel.getCorrectAnswers();
        int wrongAnswers = tokenViewModel.getWrongAnswers();

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.token_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}
