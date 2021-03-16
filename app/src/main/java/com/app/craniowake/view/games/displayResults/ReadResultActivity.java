package com.app.craniowake.view.games.displayResults;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.craniowake.R;
import com.app.craniowake.view.activityHelper.IntentHolder;
/**
 * Results of the following Tests are Displayed: ReadActivity
 */
public class ReadResultActivity extends BaseResultActivity {

    /**
     * displays gameName and total number of readingMistakes
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_read);

        TextView inputGameName = findViewById(R.id.inputGameName);
        TextView inputMistakes = findViewById(R.id.inputMistakes);

        Intent intent = getIntent();
        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        int mistakes = intent.getIntExtra(IntentHolder.READ_MISTAKES, 0);

        inputGameName.setText(gameName);
        inputMistakes.setText(Integer.toString(mistakes));
    }
}
