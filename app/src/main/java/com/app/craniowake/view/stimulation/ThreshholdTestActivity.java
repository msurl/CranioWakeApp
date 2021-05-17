package com.app.craniowake.view.stimulation;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.stimulation.ThresholdTest;
import com.app.craniowake.databinding.EventTreshholdTestStimulationBinding;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.ThresholdTestViewModel;

/**
 * THIS CLASS IS NOT PART OF THE FINISHED APP. CLASS IS FOR UPDATE TO CRANIOWAKE 2.0. PLEASE IGNORE
 */
public class ThreshholdTestActivity extends StimulationActivity {

    private ThresholdTestViewModel thresholdTestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventTreshholdTestStimulationBinding binding = DataBindingUtil.setContentView(this, R.layout.event_treshhold_test_stimulation);
        thresholdTestViewModel = new ViewModelProvider(this).get(ThresholdTestViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewmodel(thresholdTestViewModel);
    }

    @Override
    void saveTest() {
        thresholdTestViewModel.insert();
    }
}

