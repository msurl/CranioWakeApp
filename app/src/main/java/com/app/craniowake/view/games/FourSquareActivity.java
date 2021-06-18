package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.FourSquareGame;
import com.app.craniowake.databinding.ActFourSquareTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.FourSquareResultActivity;
import com.app.craniowake.view.viewModel.FourSquareViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the Four Quadrant Test (Vier Quadranten Test)
 */
public class FourSquareActivity extends OperationActivity {

    FourSquareViewModel fourSquareViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fourSquareViewModel = new ViewModelProvider(this).get(FourSquareViewModel.class);

        ActFourSquareTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_four_square_test);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(fourSquareViewModel);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    public void nextPicture(View view) {
        fourSquareViewModel.nextPicture(getCurrentOperationId());
    }

    private int calcSquares(int counterSquare1, int counterSquare2, int counterSquare3, int counterSquare4) {
        return counterSquare1 + counterSquare2 + counterSquare3 + counterSquare4;
    }

    private int calcWrongSquares(int gameRoundCounter, int counterSquare1, int counterSquare2, int counterSquare3, int counterSquare4) {
        return (gameRoundCounter * 4) - calcSquares(counterSquare1, counterSquare2, counterSquare3, counterSquare4);
    }

    /**
     * saves results in an Intent and opens new FourSquareResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishFourSquareGame(View view) {
        Intent intent = new Intent(this, FourSquareResultActivity.class);

        int gameRoundCounterMode1 = fourSquareViewModel.getGameRoundCounter();
        int gameRoundCounterMode2 = fourSquareViewModel.getGameRoundCounterFaces();

        int counterSquare1Mode1 = fourSquareViewModel.getCounterSquare1();
        int counterSquare1Mode2 = fourSquareViewModel.getCounterSquare1Faces();
        int counterSquare2Mode1 = fourSquareViewModel.getCounterSquare2();
        int counterSquare2Mode2 = fourSquareViewModel.getCounterSquare2Faces();
        int counterSquare3Mode1 = fourSquareViewModel.getCounterSquare3();
        int counterSquare3Mode2 = fourSquareViewModel.getCounterSquare3Faces();
        int counterSquare4Mode1 = fourSquareViewModel.getCounterSquare4();
        int counterSquare4Mode2 = fourSquareViewModel.getCounterSquare4Faces();

        int allWrongSquares = calcWrongSquares(gameRoundCounterMode1, counterSquare1Mode1, counterSquare2Mode1, counterSquare3Mode1, counterSquare4Mode1) -
                calcWrongSquares(gameRoundCounterMode2, counterSquare1Mode2, counterSquare2Mode2, counterSquare3Mode2, counterSquare4Mode2);


        int allSquares = calcSquares(counterSquare1Mode1, counterSquare2Mode1, counterSquare3Mode1, counterSquare4Mode1) +
                calcSquares(counterSquare1Mode2, counterSquare2Mode2, counterSquare3Mode2, counterSquare4Mode2);

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.four_square_test));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, allSquares);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, allWrongSquares);

        intent.putExtra(IntentHolder.RUNS, (gameRoundCounterMode1 + gameRoundCounterMode2));
        intent.putExtra(IntentHolder.FIRST_SQUARE, (counterSquare1Mode1 + counterSquare1Mode2));
        intent.putExtra(IntentHolder.SECOND_SQUARE, (counterSquare2Mode1 + counterSquare2Mode2));
        intent.putExtra(IntentHolder.THIRD_SQUARE, (counterSquare3Mode1 + counterSquare3Mode2));
        intent.putExtra(IntentHolder.FOURTH_SQUARE, (counterSquare4Mode1 + counterSquare4Mode2));

        intent.putExtra(IntentHolder.RUNS_OBJECT_MODE, gameRoundCounterMode1);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_OBJECT_MODE, calcSquares(counterSquare1Mode1, counterSquare2Mode1, counterSquare3Mode1, counterSquare4Mode1));
        intent.putExtra(IntentHolder.WRONG_ANSWERS_OBJECT_MODE, calcWrongSquares(gameRoundCounterMode1, counterSquare1Mode1, counterSquare2Mode1, counterSquare3Mode1, counterSquare4Mode1));
        intent.putExtra(IntentHolder.FIRST_SQUARE_MODE_1, counterSquare1Mode1);
        intent.putExtra(IntentHolder.SECOND_SQUARE_MODE_1, counterSquare2Mode1);
        intent.putExtra(IntentHolder.THIRD_SQUARE_MODE_1, counterSquare3Mode1);
        intent.putExtra(IntentHolder.FOURTH_SQUARE_MODE_1, counterSquare4Mode1);

        intent.putExtra(IntentHolder.RUNS_FACE_MODE, gameRoundCounterMode2);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_FACE_MODE, calcSquares(counterSquare1Mode2, counterSquare2Mode2, counterSquare3Mode2, counterSquare4Mode2));
        intent.putExtra(IntentHolder.WRONG_ANSWERS_FACE_MODE, calcWrongSquares(gameRoundCounterMode2, counterSquare1Mode2, counterSquare2Mode2, counterSquare3Mode2, counterSquare4Mode2));
        intent.putExtra(IntentHolder.FIRST_SQUARE_MODE_2, counterSquare1Mode2);
        intent.putExtra(IntentHolder.SECOND_SQUARE_MODE_2, counterSquare2Mode2);
        intent.putExtra(IntentHolder.THIRD_SQUARE_MODE_2, counterSquare3Mode2);
        intent.putExtra(IntentHolder.FOURTH_SQUARE_MODE_2, counterSquare4Mode2);

        finish();
        startActivity(intent);
    }
}
