package com.app.craniowake.view.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.databinding.ActPictureTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.ModiBaseResultActivity;
import com.app.craniowake.view.viewModel.PictureViewModel;

/**
 * Implementation of the Picture Test
 */
public class PictureActivity extends OperationActivity {

    PictureViewModel pictureViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pictureViewModel = new ViewModelProvider(this).get(PictureViewModel.class);
        pictureViewModel.setOperationId(getCurrentOperationId());
        // TODO: An dieser Stelle prüfen, ob es zulässig ist, dass das ViewModel eine (indirekte) Referenz auf die zugehörige Activity hält. Ansonsten onClickListener in die Activity verlegen!
        pictureViewModel.addAnswerConsumer(this::playSound);

        ActPictureTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_picture_test);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(pictureViewModel);


        setUiElements();
    }


    public void playSound(boolean correctAnswer) {
        if(correctAnswer){
            playSuccessSound();
        }
        else {
            playWrongSound();
        }
    }

    private void setUiElements() {
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * saves results in an Intent and opens new ModiBaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishPictureGame(View view) {
        Intent intent = new Intent(this, ModiBaseResultActivity.class);

        int correctAnswerFaceMode = pictureViewModel.getCorrectAnswersFaceMode();
        int wrongAnswerFaceMode = pictureViewModel.getWrongAnswersFaceMode();

        int correctAnswersObjectMode = pictureViewModel.getCorrectAnswersObjectMode();
        int wrongAnswersObjectMode = pictureViewModel.getWrongAnswersObjectMode();

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.pictrue_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswersObjectMode + correctAnswerFaceMode) + (wrongAnswersObjectMode + wrongAnswerFaceMode));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, (correctAnswersObjectMode + correctAnswerFaceMode));
        intent.putExtra(IntentHolder.WRONG_ANSWERS, (wrongAnswersObjectMode + wrongAnswerFaceMode));

        intent.putExtra(IntentHolder.RUNS_OBJECT_MODE, correctAnswersObjectMode + wrongAnswersObjectMode);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_OBJECT_MODE, correctAnswersObjectMode);
        intent.putExtra(IntentHolder.WRONG_ANSWERS_OBJECT_MODE, wrongAnswersObjectMode);

        intent.putExtra(IntentHolder.RUNS_FACE_MODE, correctAnswerFaceMode + wrongAnswerFaceMode);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_FACE_MODE, correctAnswerFaceMode);
        intent.putExtra(IntentHolder.WRONG_ANSWERS_FACE_MODE, wrongAnswerFaceMode);

        finish();
        startActivity(intent);
    }
}
