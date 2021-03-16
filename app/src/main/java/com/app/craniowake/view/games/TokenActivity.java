package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.TokenGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.TokenViewModel;

import java.util.Random;
/**
 * Implementation of the Token Test
 */
public class TokenActivity extends OperationActivity {

    OperationViewModel operationViewModel;
    TokenViewModel tokenViewModel;

    private String tmpToken;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_token_test);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
        generateRandomToken();
    }

    /**
     * compares tokenId to string buttonTokenId
     * @return true or false depending if token was recognized or not
     */
    private boolean compareTokenToButton(String buttonToken) {
        if (tmpToken.equals(buttonToken)) {
            correctAnswers++;
            return true;
        } else {
            wrongAnswers++;
            return false;
        }
    }

    /**
     * creates object of TokenGame and saves the answer to the database. Object is processed by the TokenViewModel
     * @param answer of patient if recognized token
     * @param token to be recognized
     */
    private void saveTokenGame(boolean answer, String token) {
        tokenViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TokenViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                TokenGame tokenGame = new TokenGame(token, answer, operation.getFkPatientId());
                tokenViewModel.addTokenGame(tokenGame);
            } catch (Exception e) {
                System.out.println("PictureGame has not been added to db");
            }
        });
    }

    private void evaluateAnswer(int id) {
        ImageButton myButton = findViewById(id);
        String buttonToken = myButton.getResources().getResourceEntryName(id);
        boolean answer = compareTokenToButton(buttonToken);
        saveTokenGame(answer, buttonToken);
        generateRandomToken();
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
     * following two methods first generates a random tokenString(size,color,shape) and displays it on screen
     */

    private void generateRandomToken() {
        String[] allToken = getResources().getStringArray(R.array.token);
        Random randomTokenName = new Random();
        int tmpRandom = randomTokenName.nextInt(allToken.length);
        tmpToken = allToken[tmpRandom];
        displayRandomToken(tmpRandom);
    }

    private void displayRandomToken(int randomize) {
        String[] randomToken = getResources().getStringArray(R.array.tokenView);
        TextView textView = findViewById(R.id.findTheToken);
        textView.setText(randomToken[randomize]);
    }

    @SuppressLint("NonConstantResourceId")
    public void clickOnToken(View view) {
        evaluateAnswer(view.getId());
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     * @param view method is called when button "finish game" is clicked
     */
    public void finishTokenGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.token_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}
