package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.ReactionGame;
import com.app.craniowake.databinding.ActReactionTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.activityHelper.StopWatch;
import com.app.craniowake.view.games.displayResults.ReactionResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.ReactionViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Implementation of the Reaction Test
 */
public class ReactionActivity extends OperationActivity {

    ReactionViewModel reactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reaction_test);

        reactionViewModel = new ViewModelProvider(this).get(ReactionViewModel.class);

        ActReactionTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_reaction_test);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(reactionViewModel);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }


    /**
     * generates delaytime between red and blue screen
     *
     * @return random delaytime between 2000 and 4000 ms
     */
    private long generateRandomTime() {
        long leftLimit = 2000L;
        long rightLimit = 4000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }


    /**
     * creates thread to simulate and animation of the changing screen color
     *
     * @param mHandler allows to send and process message and runnable objects associated with a thread's MessageQueue
     */
    @SuppressLint("ClickableViewAccessibility")
    private void startReactionAnimation(final Handler mHandler) {

        final long[] sleeper = {1250};

        new Thread(() -> {
            int i = 0;
            final int[] counter = {0};
            while (!reactionViewModel.getShutdown().getValue() && i < 4) {
                try {
                    mHandler.post(() -> {
                        counter[0]++;
                        if (counter[0] == 2) {
                            sleeper[0] = generateRandomTime();
                            //mache random zeit bis zum vierten durchlauf
                        }

                        if (counter[0] == 3) {
//                            reactionGameBackground.setBackgroundColor(Color.BLUE);
                            reactionViewModel.getStopWatch().startTime();
                            reactionViewModel.getStarted().setValue(true);
                            //starte timer bis geklickt wird
                            reactionViewModel.getClicked().setValue(true);
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


    public void click(View v) {
        if (reactionViewModel.getClicked().getValue() && reactionViewModel.getStarted().getValue()) {
            reactionViewModel.getStopWatch().stopTime();
            reactionViewModel.getRounds()[reactionViewModel.getCounter()] = reactionViewModel.getStopWatch().getTimer();
            reactionViewModel.incrementCounter();
            reactionViewModel.addReactionGame(getCurrentOperationId());
            if (reactionViewModel.getCounter() == 5) {
                reactionViewModel.getShutdown().setValue(true);
                finishReactionGame(v);
            }
            reactionViewModel.getClicked().setValue(false);
            reactionViewModel.getStarted().setValue(false);

            startReactionAnimation(new Handler(Looper.myLooper()));
        }
        else if(!reactionViewModel.getStarted().getValue()){
            startReactionAnimation(new Handler(Looper.myLooper()));
        }
    }

    /**
     * saves results in an Intent and opens new ReactionResultActivity. The current Activity gets destroyed when left.
     *
     *  method is called when button "finish game" is clicked
     */
    public void finishReactionGame(View v) {
        Intent intent = new Intent(this, ReactionResultActivity.class);

        String[] rounds = reactionViewModel.getRounds();

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