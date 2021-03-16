package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.ReactionGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.activityHelper.StopWatch;
import com.app.craniowake.view.games.displayResults.ReactionResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.ReactionViewModel;
/**
 * Implementation of the Reaction Test
 */
public class ReactionActivity extends OperationActivity {

    private final String[] rounds = new String[5];
    ReactionViewModel reactionViewModel;
    OperationViewModel operationViewModel;
    private int flags;
    private boolean shutdown = false;
    private boolean clicked = false;
    private boolean started = false;
    private int counter = 0;
    private StopWatch stopWatch;

    /**
     * reacts on motion of patient when touched on screen
     * when touched down (action.down) the reaction time of the patient is saved
     */
    public View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            FrameLayout reactionGameBackground = findViewById(R.id.reaction_background_color_id);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (clicked && started) {
                        stopWatch.stopTime();
                        rounds[counter] = stopWatch.getTimer();
                        counter++;
                        saveReactionGame(stopWatch.getMilliSeconds());
                        if (counter == 5) {
                            shutdown = true;
                            finishReactionGame(v);
                        }
                        reactionGameBackground.setBackgroundColor(Color.RED);
                        clicked = false;
                        started = false;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reaction_test);
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();

        Handler handler = new Handler();
        initiateRounds();
        TextView txtTimer = findViewById(R.id.textViewTimer);
        stopWatch = new StopWatch(handler);
        stopWatch.setTxtTimer(txtTimer);
    }

    /**
     * default input for time in each round before reaction starts
     */
    private void initiateRounds() {
        for (int i = 0; i < 5; i++) {
            rounds[i] = "-";
        }
    }

    public void startReactionTest(View view) {
        Handler mHandler = new Handler();
        if (!clicked && !started) {
            started = true;
            startShowingNumbers(mHandler);
        }
    }

    private void startShowingNumbers(Handler mHandler) {
        startReactionAnimation(setNumberOnView(), mHandler);
    }

    private TextView setNumberOnView() {
        return findViewById(R.id.reaction_textview_tap_id);
    }

    /**
     * generates delaytime between red and blue screen
     * @return random delaytime between 2000 and 4000 ms
     */
    private long generateRandomTime() {
        long leftLimit = 2000L;
        long rightLimit = 4000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    /**
     * creates object of ReactionGame and saves the answer to the database. Object is processed by the ReactionViewModel
     * @param milliSeconds reaction time of patient
     */
    private void saveReactionGame(int milliSeconds) {
        reactionViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ReactionViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                ReactionGame reactionGame = new ReactionGame(milliSeconds, operation.getFkPatientId());
                reactionViewModel.addReactionGame(reactionGame);
            } catch (Exception e) {
                System.out.println("PictureGame has not been added to db");
            }
        });
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
     * creates thread to simulate and animation of the changing screen color
     * @param textView view on which the changing screen is displayed
     * @param mHandler allows to send and process message and runnable objects associated with a thread's MessageQueue
     */
    @SuppressLint("ClickableViewAccessibility")
    private void startReactionAnimation(final TextView textView, final Handler mHandler) {

        final long[] sleeper = {1250};
        flags = 0;
        final FrameLayout reactionGameBackground = findViewById(R.id.reaction_background_color_id);
        reactionGameBackground.setOnTouchListener(handleTouch);

        new Thread(() -> {
            int i = 0;
            final int[] counter = {0};
            while (!shutdown && i < 4) {
                try {
                    mHandler.post(() -> {
                        counter[0]++;
                        if (flags == 0) {
                            textView.setText("-1");
                            flags = 1;
                        } else {
                            textView.setText("1");
                            flags = 0;
                        }
                        if (counter[0] == 3) {
                            sleeper[0] = generateRandomTime();
                            //mache random zeit bis zum vierten durchlauf
                        }

                        if (counter[0] == 4) {
                            reactionGameBackground.setBackgroundColor(Color.BLUE);
                            stopWatch.startTime();
                            //starte timer bis geklickt wird
                            clicked = true;
                        }
                    });
                    Thread.sleep(sleeper[0]); //erwartet long -> setze random timer nach dem dritten mal
                } catch (Exception e) {
                    System.out.println("fehler");
                }
                i++;
            }
        }).start();
    }

    /**
     * saves results in an Intent and opens new ReactionResultActivity. The current Activity gets destroyed when left.
     * @param view method is called when button "finish game" is clicked
     */
    public void finishReactionGame(View view) {
        Intent intent = new Intent(this, ReactionResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, "Reaction Test");
        intent.putExtra(IntentHolder.REACTION_ROUND_1, rounds[0] + "");
        intent.putExtra(IntentHolder.REACTION_ROUND_2, rounds[1] + "");
        intent.putExtra(IntentHolder.REACTION_ROUND_3, rounds[2] + "");
        intent.putExtra(IntentHolder.REACTION_ROUND_4, rounds[3] + "");
        intent.putExtra(IntentHolder.REACTION_ROUND_5, rounds[4] + "");

        finish();
        startActivity(intent);
    }

}
