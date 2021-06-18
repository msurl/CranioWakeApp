package com.app.craniowake.view.games;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.databinding.ActCalcTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.CalculatingViewModel;

/**
 * Implementation of the Calculus Test
 */
public class CalculusActivity extends OperationActivity {

    private CalculatingViewModel calculatingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActCalcTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_calc_test);
        calculatingViewModel = new ViewModelProvider(this).get(CalculatingViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(calculatingViewModel);
        calculatingViewModel.setOperationId(getCurrentOperationId());

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishCalcGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        int correct = calculatingViewModel.getCorrect();
        int wrong = calculatingViewModel.getWrong();
        int runs = correct + wrong;

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.math_test));
        intent.putExtra(IntentHolder.RUNS, runs);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correct);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrong);

        finish();
        startActivity(intent);
    }

}