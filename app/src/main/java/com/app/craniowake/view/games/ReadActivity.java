package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.ReadGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.ReadResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.ReadViewModel;

import java.util.Random;
/**
 * Implementation of the Read Test (Lese Test)
 */
public class ReadActivity extends OperationActivity {

    private final int[] textCollection = {R.drawable.text_dd, R.drawable.text_fortunadd_deu};
    OperationViewModel operationViewModel;
    ReadViewModel readViewModel;
    private int mistakeCounter = 0;
    private int totalMistakes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_read_test);
        ImageView btn = findViewById(R.id.read_test_view);
        btn.setImageResource(R.drawable.text_dd);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * sets second text
     * @param view method called when clicked on text
     */
    public void setNextText(View view) {
        mistakeCounter = 0;
        setMistakeCounter();
        Random random = new Random();
        ImageView btn = findViewById(R.id.read_test_view);
        btn.setImageResource(textCollection[random.nextInt(textCollection.length)]);
    }

    /**
     * adds or decreases mistake counter depending on given answer by patient
     */
    @SuppressLint("NonConstantResourceId")
    public void addMistake(View view) {
        switch (view.getId()) {
            case R.id.addMistakeButton:
                mistakeCounter++;
                totalMistakes++;
                break;
            case R.id.subMistakeButton:
                mistakeCounter--;
                checkMistakeCounter();
                break;
            default:
                System.out.println("hat nicht geklappt");
                break;
        }
        setMistakeCounter();
    }

    @SuppressLint("SetTextI18n")
    private void setMistakeCounter() {
        TextView textView = findViewById(R.id.mistakeCounterTextInput);
        textView.setText(Integer.toString(mistakeCounter));
    }

    /**
     * counter never negative
     */
    private void checkMistakeCounter() {
        if (mistakeCounter < 0) {
            mistakeCounter = 0;
        }
    }

    /**
     * creates object of ReadGame and saves the answer to the database. Object is processed by the ReadViewModel
     * @param mistakeCounter amount of reading mistakes made by patient
     */
    private void saveReadGame(int mistakeCounter) {
        readViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ReadViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                ReadGame readGame = new ReadGame(mistakeCounter, operation.getFkPatientId());
                readViewModel.addReadGame(readGame);
            } catch (Exception e) {
                System.out.println("PictureGame has not been added to db");
            }
        });
    }

    /**
     * returns string of dateTime when current operation was created
     * its used as an identifier
     */
    private String getCurrentOperationId() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_DATE);
    }

    /**
     * saves results in an Intent and opens new ReadResultActivity. The current Activity gets destroyed when left.
     * @param view method is called when button "finish game" is clicked
     */
    public void finishReadGame(View view) {
        saveReadGame(totalMistakes);
        Intent intent = new Intent(this, ReadResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, "Reading Test");
        intent.putExtra(IntentHolder.READ_MISTAKES, totalMistakes);

        finish();
        startActivity(intent);
    }
}
