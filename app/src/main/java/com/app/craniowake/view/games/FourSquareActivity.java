package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.FourSquareGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.FourSquareResultActivity;
import com.app.craniowake.view.viewModel.FourSquareViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the Four Quadrant Test (Vier Quadranten Test)
 */
public class FourSquareActivity extends OperationActivity {

    private final int[] pictureFourSquareCollection = {R.drawable.fs_bauarbeiter_geschenk_traktor_flugzeug, R.drawable.fs_baum_auto_leuchtturm_zahnarzt, R.drawable.fs_brotkorb_handschellen_spritze_baum, R.drawable.fs_erdbeere_zug_koch_blumen, R.drawable.fs_haus_fahrrad_apfel_leer, R.drawable.fs_herbstblaetter_wolke_schwan_schiff, R.drawable.fs_katze_buecher_ballerina_schiff, R.drawable.fs_lkw_pc_leer_jaeger, R.drawable.fs_sessel_kerze_kuchen_hase, R.drawable.fs_strand_erde_schloss_lampe, R.drawable.fs_stuhl_roller_tisch_stift};
    private final int[] pictureFourSquareCollectionFaces = {R.drawable.fs_bartsimpson_nowitzki_donaldduck_kahn, R.drawable.fs_einstein_mozart_mrbean_sissi, R.drawable.fs_gauck_steffigraf_borisbecker_obama, R.drawable.fs_guentherjauch_beatles_mickeymaus_leer, R.drawable.fs_kennedy_pipilangstrumpf_totehosen_beckenbauer, R.drawable.fs_monroe_klumm_kohl_gotschalk};
    FourSquareViewModel fourSquareViewModel;
    OperationViewModel operationViewModel;
    private int gameMode = 1;
    private int currentPic;

    private boolean patientRecognized4Square1;
    private boolean patientRecognized4Square2;
    private boolean patientRecognized4Square3;
    private boolean patientRecognized4Square4;

    //results
    private int gameRoundCounterMode1 = 0;
    private int counterSquare1Mode1;
    private int counterSquare2Mode1;
    private int counterSquare3Mode1;
    private int counterSquare4Mode1;

    private int gameRoundCounterMode2 = 0;
    private int counterSquare1Mode2;
    private int counterSquare2Mode2;
    private int counterSquare3Mode2;
    private int counterSquare4Mode2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_four_square_test);
        ImageView btn = findViewById(R.id.fourSquare_test_view);
        btn.setImageResource(R.drawable.fs_leer);
        currentPic = R.drawable.fs_leer;

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * set next picture on display
     *
     * @param view method is called when picture is clicked
     */
    public void setNextPicture(View view) {
        ImageView btn = findViewById(R.id.fourSquare_test_view);
        saveFourSquareGame(check(currentPic), patientRecognized4Square1, patientRecognized4Square2, patientRecognized4Square3, patientRecognized4Square4);
        btn.setImageResource(returnCurrentPic());
        setButtonsToDefault();
    }

    /**
     * returns picture depending on gameMode(Faces/Objects)
     *
     * @return id of resource (picture)
     */
    private int returnCurrentPic() {
        Random random = new Random();
        if (gameMode == 1) {
            return currentPic = pictureFourSquareCollection[random.nextInt(pictureFourSquareCollection.length)];
        } else {
            return currentPic = pictureFourSquareCollectionFaces[random.nextInt(pictureFourSquareCollectionFaces.length)];
        }
    }

    /**
     * returns fileName of picture
     *
     * @param id id of resource (picture)
     * @return name of resource with given id
     */
    private String check(int id) { // R.id.bigGreenCircle
        return getResources().getResourceName(id);
    }

    /**
     * sets mode to 1=Object or 0=Faces
     *
     * @param view method is triggered when "change mode" Button is clicked
     */
    public void setFourSquareGameMode(View view) {
        gameMode++;
        setNextPicture(view);
        if (gameMode > 1) {
            gameMode = 0;
        }
    }

    /**
     * initiates button color changes depending on the square
     *
     * @param view method is triggered when one of the four buttons to record answer is clicked
     */
    @SuppressLint("NonConstantResourceId")
    public void setButtonColorClicked(View view) {
        switch (view.getId()) {
            case R.id.firstPicFourSquareButton:
                this.patientRecognized4Square1 = setButtonColor(R.id.firstPicFourSquareButton, this.patientRecognized4Square1);
                break;
            case R.id.secondPicFourSquareButton:
                this.patientRecognized4Square2 = setButtonColor(R.id.secondPicFourSquareButton, this.patientRecognized4Square2);
                break;
            case R.id.thirdPicFourSquareButton:
                this.patientRecognized4Square3 = setButtonColor(R.id.thirdPicFourSquareButton, this.patientRecognized4Square3);
                break;
            case R.id.fourthPicFourSquareButton:
                this.patientRecognized4Square4 = setButtonColor(R.id.fourthPicFourSquareButton, this.patientRecognized4Square4);
        }
    }

    /**
     * creates object of FourSquareGame and saves the answer to the database. Object is processed by the FourSquareViewModel
     *
     * @param pictureName picture to be recognized
     * @param firstSquare answer if patient recognized first square or not
     *                    secondSquare, thirdSquare, fourthSquare analogue
     */
    private void saveFourSquareGame(String pictureName, boolean firstSquare, boolean secondSquare, boolean thirdSquare, boolean fourthSquare) {
        fourSquareViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(FourSquareViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate((LocalDateTime) getCurrentOperationId()).observe(this, operation -> {
            try {
                FourSquareGame fourSquareGame;
                if(stimulated)
                    fourSquareGame = new FourSquareGame(pictureName, firstSquare, secondSquare, thirdSquare, fourthSquare, stimulation, operation.getOperationId());
                else
                    fourSquareGame = new FourSquareGame(pictureName, firstSquare, secondSquare, thirdSquare, fourthSquare, operation.getOperationId());
                fourSquareViewModel.addFourSquareGame(fourSquareGame);
            } catch (Exception e) {
                System.out.println("PictureGame has not been added to db");
            }
            finally {
                stimulated = false;
            }
        });
    }

    /**
     * returns string of dateTime when current operation was created t
     * its used as an identifier
     */
    private Serializable getCurrentOperationId() {
        Intent intent = getIntent();
        return intent.getSerializableExtra(IntentHolder.OPERATION_DATE);
    }

    /**
     * changes button color if square has been recognized
     *
     * @param id               from squareButton
     * @param recognizedSquare determines if color changes to green = true or grey = false
     */
    private boolean setButtonColor(int id, boolean recognizedSquare) {
        Button clickedButton = findViewById(id);
        if (!recognizedSquare) {
            clickedButton.setBackgroundColor(Color.GREEN);
            return true;
        } else {
            clickedButton.setBackgroundColor(Color.GRAY);
            return false;
        }
    }

    /**
     * sets game to default when a new round starts.
     */
    private void setButtonsToDefault() {
        countRecognizedSquares();
        Button clickedButton1 = findViewById(R.id.firstPicFourSquareButton);
        Button clickedButton2 = findViewById(R.id.secondPicFourSquareButton);
        Button clickedButton3 = findViewById(R.id.thirdPicFourSquareButton);
        Button clickedButton4 = findViewById(R.id.fourthPicFourSquareButton);

        clickedButton1.setBackgroundColor(Color.LTGRAY);
        clickedButton2.setBackgroundColor(Color.LTGRAY);
        clickedButton3.setBackgroundColor(Color.LTGRAY);
        clickedButton4.setBackgroundColor(Color.LTGRAY);

        patientRecognized4Square1 = false;
        patientRecognized4Square2 = false;
        patientRecognized4Square3 = false;
        patientRecognized4Square4 = false;
    }

    /**
     * the following methods count and calculate the correct and wrong answers for all rounds, per square and for each mode
     * values are send to FourSquareResultActivity for display
     */

    private void countRecognizedSquares() {
        setCorrectModesCounter();
        if (patientRecognized4Square1) {
            if (gameMode == 1) {
                this.counterSquare1Mode1++;
            } else {
                this.counterSquare1Mode2++;
            }
        }
        if (patientRecognized4Square2) {
            if (gameMode == 1) {
                this.counterSquare2Mode1++;
            } else {
                this.counterSquare2Mode2++;
            }
        }
        if (patientRecognized4Square3) {
            if (gameMode == 1) {
                this.counterSquare3Mode1++;
            } else {
                this.counterSquare3Mode2++;
            }
        }
        if (patientRecognized4Square4) {
            if (gameMode == 1) {
                this.counterSquare4Mode1++;
            } else {
                this.counterSquare4Mode2++;
            }
        }
    }

    private void setCorrectModesCounter() {
        if (gameMode == 1) {
            gameRoundCounterMode1++;
        } else {
            gameRoundCounterMode2++;
        }
    }

    private int calcSquares(int counterSquare1, int counterSquare2, int counterSquare3, int counterSquare4) {
        return counterSquare1 + counterSquare2 + counterSquare3 + counterSquare4;
    }

    private int calcAllSquares() {
        return (counterSquare1Mode1 + counterSquare1Mode2) + (counterSquare2Mode1 + counterSquare2Mode2) + (counterSquare3Mode1 + counterSquare3Mode2) + (counterSquare4Mode1 + counterSquare4Mode2);
    }

    private int calcWrongSquares(int gameRoundCounter, int counterSquare1, int counterSquare2, int counterSquare3, int counterSquare4) {
        return (gameRoundCounter * 4) - calcSquares(counterSquare1, counterSquare2, counterSquare3, counterSquare4);
    }

    private int calcAllWrongSquares(int gameRoundCounter) {
        return (gameRoundCounter * 4) - calcAllSquares();
    }

    /**
     * saves results in an Intent and opens new FourSquareResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishFourSquareGame(View view) {
        Intent intent = new Intent(this, FourSquareResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.four_square_test));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, calcAllSquares());
        intent.putExtra(IntentHolder.WRONG_ANSWERS, calcAllWrongSquares(gameRoundCounterMode1 + gameRoundCounterMode2));

        intent.putExtra(IntentHolder.RUNS, (gameRoundCounterMode1 + gameRoundCounterMode2));
        intent.putExtra(IntentHolder.FIRST_SQUARE, (counterSquare1Mode1 + counterSquare1Mode2));
        intent.putExtra(IntentHolder.SECOND_SQUARE, (counterSquare2Mode1 + counterSquare2Mode2));
        intent.putExtra(IntentHolder.THIRD_SQUARE, (counterSquare3Mode1 + counterSquare3Mode2));
        intent.putExtra(IntentHolder.FOURTH_SQUARE, (counterSquare4Mode1 + counterSquare4Mode2));

        intent.putExtra(IntentHolder.RUNS_MODE_1, gameRoundCounterMode1);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_MODE_1, calcSquares(counterSquare1Mode1, counterSquare2Mode1, counterSquare3Mode1, counterSquare4Mode1));
        intent.putExtra(IntentHolder.WRONG_ANSWERS_MODE_1, calcWrongSquares(gameRoundCounterMode1, counterSquare1Mode1, counterSquare2Mode1, counterSquare3Mode1, counterSquare4Mode1));
        intent.putExtra(IntentHolder.FIRST_SQUARE_MODE_1, counterSquare1Mode1);
        intent.putExtra(IntentHolder.SECOND_SQUARE_MODE_1, counterSquare2Mode1);
        intent.putExtra(IntentHolder.THIRD_SQUARE_MODE_1, counterSquare3Mode1);
        intent.putExtra(IntentHolder.FOURTH_SQUARE_MODE_1, counterSquare4Mode1);

        intent.putExtra(IntentHolder.RUNS_MODE_2, gameRoundCounterMode2);
        intent.putExtra(IntentHolder.CORRECT_ANSWERS_MODE_2, calcSquares(counterSquare1Mode2, counterSquare2Mode2, counterSquare3Mode2, counterSquare4Mode2));
        intent.putExtra(IntentHolder.WRONG_ANSWERS_MODE_2, calcWrongSquares(gameRoundCounterMode2, counterSquare1Mode2, counterSquare2Mode2, counterSquare3Mode2, counterSquare4Mode2));
        intent.putExtra(IntentHolder.FIRST_SQUARE_MODE_2, counterSquare1Mode2);
        intent.putExtra(IntentHolder.SECOND_SQUARE_MODE_2, counterSquare2Mode2);
        intent.putExtra(IntentHolder.THIRD_SQUARE_MODE_2, counterSquare3Mode2);
        intent.putExtra(IntentHolder.FOURTH_SQUARE_MODE_2, counterSquare4Mode2);

        finish();
        startActivity(intent);
    }
}
