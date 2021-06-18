package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.ReadGame;
import com.app.craniowake.databinding.ActReadTestBinding;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.ReadResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.ReadViewModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the Read Test (Lese Test)
 */
public class ReadActivity extends OperationActivity {

    ReadViewModel readViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readViewModel = new ViewModelProvider(this).get(ReadViewModel.class);
        readViewModel.setOperationId(getCurrentOperationId());

        ActReadTestBinding binding = DataBindingUtil.setContentView(this, R.layout.act_read_test);
        binding.setViewmodel(readViewModel);
        binding.setLifecycleOwner(this);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * saves results in an Intent and opens new ReadResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishReadGame(View view) {
        Intent intent = new Intent(this, ReadResultActivity.class);

        int totalMistakes = readViewModel.getTotalMistakes();

        intent.putExtra(IntentHolder.GAME_NAME, "Reading Test");
        intent.putExtra(IntentHolder.READ_MISTAKES, totalMistakes);

        finish();
        startActivity(intent);
    }
}
