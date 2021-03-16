package com.app.craniowake.view.games.displayResults;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.craniowake.R;
import com.app.craniowake.view.activityHelper.IntentHolder;
/**
 * Results of the following Tests are Displayed: ReactionActivity
 */
public class ReactionResultActivity extends BaseResultActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_reaction);
        setResultBase();
    }

    /**
     * retrieves Results for Reaction Test from Intents
     */
    protected void setResultBase() {
        Intent intent = getIntent();

        String gameName = intent.getStringExtra(IntentHolder.GAME_NAME);
        String round1 = intent.getStringExtra(IntentHolder.REACTION_ROUND_1);
        String round2 = intent.getStringExtra(IntentHolder.REACTION_ROUND_2);
        String round3 = intent.getStringExtra(IntentHolder.REACTION_ROUND_3);
        String round4 = intent.getStringExtra(IntentHolder.REACTION_ROUND_4);
        String round5 = intent.getStringExtra(IntentHolder.REACTION_ROUND_5);

        displayBaseResults(gameName, round1, round2, round3, round4, round5);
    }

    /**
     * displays results of Reaction Test for five rounds.
     * @param round1 time needed to react in 00:00.000
     *               round2-round5 analogue
     */
    void displayBaseResults(String gameName, String round1, String round2, String round3, String round4, String round5) {

        TextView inputGameName = findViewById(R.id.reaction_input_name);
        TextView inputRound1 = findViewById(R.id.reaction_inputRound1);
        TextView inputRound2 = findViewById(R.id.reaction_inputRound2);
        TextView inputRound3 = findViewById(R.id.reaction_inputRound3);
        TextView inputRound4 = findViewById(R.id.reaction_inputRound4);
        TextView inputRound5 = findViewById(R.id.reaction_inputRound5);

        inputGameName.setText(gameName);
        inputRound1.setText(round1);
        inputRound2.setText(round2);
        inputRound3.setText(round3);
        inputRound4.setText(round4);
        inputRound5.setText(round5);
    }
}