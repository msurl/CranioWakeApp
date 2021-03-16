package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.PictureGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.ModiBaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.PictureViewModel;

import java.util.Random;
/**
 * Implementation of the Picture Test
 */
public class PictureActivity extends OperationActivity {

    private final int[] pictureCollection = {R.drawable.p_apfel, R.drawable.p_ampel, R.drawable.p_auto, R.drawable.p_bahn, R.drawable.p_ballerina, R.drawable.p_baum, R.drawable.p_baumkahl, R.drawable.p_blaetter, R.drawable.p_boot, R.drawable.p_buecher, R.drawable.p_erdbeere, R.drawable.p_erde, R.drawable.p_fahrrad, R.drawable.p_flamingo, R.drawable.p_flugzeug, R.drawable.p_frosch};
    private final int[] pictureFaceCollection = {R.drawable.pf_beatles, R.drawable.pf_borisbecker, R.drawable.pf_dirknowitzki, R.drawable.pf_einstein, R.drawable.pf_franzbeckenbauer, R.drawable.pf_guentherjauch, R.drawable.pf_heidiklumm, R.drawable.pf_helmutkohl};
    OperationViewModel operationViewModel;
    PictureViewModel pictureViewModel;
    private int gameMode = 1;
    private int currentPictureId;

    private int correctAnswersMode1 = 0;
    private int wrongAnswersMode1 = 0;
    private int correctAnswersMode2 = 0;
    private int wrongAnswersMode2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_picture_test);
        setUiElements();
        setNextPicture();
    }

    /**
     * sets mode to 1=Object or 0=Faces
     * @param view method is triggered when "change mode" Button is clicked
     */
    public void setPictureGameMode(View view) {
        gameMode++;
        setNextPicture();
        if (gameMode > 1) {
            gameMode = 0;
        }
    }

    /**
     * save current answers and set next picture on display
     * @param view is called when "true" or "false" button is clicked
     */
    @SuppressLint("NonConstantResourceId")
    public void setPatientRecognizedPicture(View view) {
        savePictureGame(evaluateAnswer(view.getId()), check(currentPictureId));
        setNextPicture();
    }

    private int generateRandomPicture() {
        Random random = new Random();
        return chooseMode(random);
    }

    private void setNextPicture() {
        ImageView btn = findViewById(R.id.picture_test_view);
        btn.setImageResource(generateRandomPicture());
    }

    /**
     * returns picture depending on gameMode(Faces/Objects)
     * @return id of resource (picture)
     */
    private int chooseMode(Random random) {
        if (gameMode == 1) {
            currentPictureId = pictureCollection[random.nextInt(pictureCollection.length)];
        } else {
            currentPictureId = pictureFaceCollection[random.nextInt(pictureFaceCollection.length)];
        }
        return currentPictureId;
    }

    /**
     * creates object of PictureGame and saves the answer to the database. Object is processed by the PictureViewModel
     * @param answer of patient if recognized picture
     * @param pictureName picture to be recognized
     */
    private void savePictureGame(boolean answer, String pictureName) {
        pictureViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PictureViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                PictureGame pictureGame = new PictureGame(pictureName, answer, operation.getFkPatientId());
                pictureViewModel.addPictureGame(pictureGame);
            } catch (Exception e) {
                System.out.println("PictureGame has not been added to db");
            }
        });
    }

    /**
     * returns fileName of picture
     * @param id id of resource (picture)
     * @return name of resource with given id
     */
    private String check(int id) { // R.id.bigGreenCircle
        return getResources().getResourceName(id);
    }

    /**
     * evaluates answer given by the id of the button which was clicked. Also increases general counter of correct or wrong answers
     * @param id of button "true" or "false"
     */
    @SuppressLint("NonConstantResourceId")
    private boolean evaluateAnswer(int id) {
        switch (id) {
            case R.id.pictureTrueButton:
                setCorrectModesCounter();
                return true;
            case R.id.pictureFalseButton:
                setWrongModesCounter();
                return false;
            default:
                break;
        }
        return false;
    }

    /**
     * returns string of dateTime when current operation was created t
     * its used as an identifier
     */
    private String getCurrentOperationId() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_DATE);
    }

    private void setUiElements() {
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * answer given by patient is added to general counter of correct and wrong answers for each mode.
     */

    private void setCorrectModesCounter() {
        if (gameMode == 1) {
            correctAnswersMode1++;
        } else {
            correctAnswersMode2++;
        }
    }

    private void setWrongModesCounter() {
        if (gameMode == 1) {
            wrongAnswersMode1++;
        } else {
            wrongAnswersMode2++;
        }
    }

    /**
     * saves results in an Intent and opens new ModiBaseResultActivity. The current Activity gets destroyed when left.
     * @param view method is called when button "finish game" is clicked
     */
    public void finishPictureGame(View view) {
        Intent intent = new Intent(this, ModiBaseResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.pictrue_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswersMode1 + correctAnswersMode2) + (wrongAnswersMode1 + wrongAnswersMode2));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, (correctAnswersMode1 + correctAnswersMode2));
        intent.putExtra(IntentHolder.WRONG_ANSWERS, (wrongAnswersMode1 + wrongAnswersMode2));

        intent.putExtra(IntentHolder.RUNS_MODE_1, correctAnswersMode1 + wrongAnswersMode1);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_MODE_1, correctAnswersMode1);
        intent.putExtra(IntentHolder.WRONG_ANSWERS_MODE_1, wrongAnswersMode1);

        intent.putExtra(IntentHolder.RUNS_MODE_2, correctAnswersMode2 + wrongAnswersMode2);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_MODE_2, correctAnswersMode2);
        intent.putExtra(IntentHolder.WRONG_ANSWERS_MODE_2, wrongAnswersMode2);

        finish();
        startActivity(intent);
    }
}
